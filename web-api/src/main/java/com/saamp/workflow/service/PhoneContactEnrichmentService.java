package com.saamp.workflow.service;

import com.saamp.olddb.repository.OldContactRepository;
import com.saamp.workflow.entity.PhoneContactEntity;
import com.saamp.workflow.entity.PhoneUserEntity;
import com.saamp.workflow.repository.PhoneContactRepository;
import com.saamp.workflow.repository.PhoneUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

@Service
public class PhoneContactEnrichmentService {

    private static final Logger log = LoggerFactory.getLogger(PhoneContactEnrichmentService.class);

    private final PhoneContactRepository phoneContactRepository;
    private final PhoneUserRepository phoneUserRepository;
    private final OldContactLookupService oldContactLookupService;

    @PersistenceContext
    private EntityManager entityManager;

    public PhoneContactEnrichmentService(
            PhoneContactRepository phoneContactRepository,
            PhoneUserRepository phoneUserRepository,
            OldContactLookupService oldContactLookupService
    ) {
        this.phoneContactRepository = phoneContactRepository;
        this.phoneUserRepository = phoneUserRepository;
        this.oldContactLookupService = oldContactLookupService;
    }

    /**
     * Parcourt les phone_contact non liés à un phone_user et crée un phone_user "client"
     * si l'identité est trouvée dans l'ancienne base.
     *
     * @return nombre de phone_user créés
     */
    @Transactional
    public int enrichMissingContacts() {

        int created = 0;

        // Tu peux ajuster la taille de batch
        int batchSize = 500;

        // Cache en mémoire pour éviter de requêter l'ancienne base plusieurs fois pour le même numéro
        // (gros gain si des numéros sont en doublon ou si l'ancienne base est lente)
        // Concurrent car on peut précharger les lookups en parallèle (voir plus bas)
        Map<String, Optional<OldContactRepository.ContactView>> oldLookupCache = new ConcurrentHashMap<>();

        // IMPORTANT: on avance page par page. L'ancienne version refetchait toujours la page 0
        // => boucle quasi-infinie si les premiers contacts ne sont pas enrichissables.
        int page = 0;

        while (true) {
            Instant pageStart = Instant.now();
            List<PhoneContactEntity> toEnrich =
                    phoneContactRepository.findUnmappedContacts(PageRequest.of(page, batchSize));

            if (toEnrich.isEmpty()) {
                break;
            }

            // --- Préchargement des lookups "old DB" (en parallèle, parallélisme limité) ---
            // Objectif : éviter 500 appels séquentiels vers l'ancienne base.
            // On normalise 1 seule fois par contact, puis on précharge les numéros uniques.
            Map<UUID, String> normalizedByContactId = new HashMap<>(toEnrich.size());
            Set<String> uniquePhones = toEnrich.stream()
                    .map(c -> {
                        if (c == null || c.getPhoneNumber() == null) return null;
                        String n = normalizeFrenchPhone(c.getPhoneNumber());
                        if (n != null && c.getId() != null) {
                            normalizedByContactId.put(c.getId(), n);
                        }
                        return n;
                    })
                    .filter(p -> p != null)
                    .collect(Collectors.toSet());

            // Précharge uniquement ce qu'on ne connaît pas encore
            Set<String> phonesToLookup = uniquePhones.stream()
                    .filter(p -> !oldLookupCache.containsKey(p))
                    .collect(Collectors.toSet());

            if (!phonesToLookup.isEmpty()) {
                // Limite de parallélisme (évite de saturer les pools DB)
                int parallelism = Math.min(8, Math.max(2, Runtime.getRuntime().availableProcessors() / 2));
                ForkJoinPool pool = new ForkJoinPool(parallelism);
                try {
                    pool.submit(() -> phonesToLookup.parallelStream()
                            .forEach(p -> oldLookupCache.computeIfAbsent(p, this::lookupOld))
                    ).get();
                } catch (Exception e) {
                    // En cas de souci (interruption, etc.), on retombe sur un mode séquentiel.
                    log.warn("enrichMissingContacts: bulk prefetch failed, fallback to sequential lookups", e);
                    for (String p : phonesToLookup) {
                        oldLookupCache.computeIfAbsent(p, this::lookupOld);
                    }
                } finally {
                    pool.shutdown();
                }
            }

            int createdThisPage = 0;
            List<PhoneUserEntity> toSave = new ArrayList<>(Math.min(200, toEnrich.size()));

            for (PhoneContactEntity contact : toEnrich) {
                if (contact == null || contact.getPhoneNumber() == null || contact.getPhoneNumber().isBlank()) {
                    continue;
                }

                // Normalisation (évite les ratés de lookup + réduit les requêtes)
                String normalizedPhone = (contact.getId() == null) ? null : normalizedByContactId.get(contact.getId());
                if (normalizedPhone == null) {
                    normalizedPhone = normalizeFrenchPhone(contact.getPhoneNumber());
                }
                if (normalizedPhone == null) {
                    continue;
                }

                // NOTE perf: idéalement, findUnmappedContacts() doit déjà exclure les contacts déjà liés.
                // Le check exist() par contact est très coûteux (N+1). On le retire pour accélérer.
                // Si tu as des doublons en prod, il faut plutôt renforcer la requête repo (LEFT JOIN ... WHERE phone_user.id IS NULL)
                // ou une contrainte unique côté DB.

                Optional<OldContactRepository.ContactView> oldOpt =
                        oldLookupCache.getOrDefault(normalizedPhone, Optional.empty());
                if (oldOpt.isEmpty()) {
                    continue;
                }

                OldContactRepository.ContactView old = oldOpt.get();

                // IMPORTANT: tu avais un filtre "company != null" avant
                // On le garde pour éviter de créer des "clients" sans identité exploitable.
                if (old.getCompany() == null || old.getCompany().isBlank()) {
                    continue;
                }

                PhoneUserEntity pu = new PhoneUserEntity();

                // Mapping identité : si prénom/nom présents -> personne
                // sinon -> entité (denomination)
                String fn = trimToNull(old.getFirstname());
                String ln = trimToNull(old.getLastname());

                if (fn != null || ln != null) {
                    pu.setFirstname(fn);
                    pu.setLastname(ln);
                    pu.setDenomination(null);
                } else {
                    pu.setFirstname(null);
                    pu.setLastname(null);
                    pu.setDenomination(old.getCompany());
                }

                // Company = nom de la boîte (client) (SAAMP restera interne ailleurs)
                pu.setCompany(old.getCompany());
                if(old.getClId() != null){
                    pu.setMysaampIdClient(old.getClId());
                }
                // On rattache le contact en "other" (client externe)
                pu.setPhonePro(contact);

                toSave.add(pu);
            }

            if (!toSave.isEmpty()) {
                phoneUserRepository.saveAll(toSave);
                created += toSave.size();
                createdThisPage = toSave.size();
            }

            // Évite que le contexte de persistance JPA grossisse au fil des pages
            entityManager.flush();
            entityManager.clear();

            log.info(
                    "enrichMissingContacts: page={} batchSize={} -> +{} users (total={}) in {}",
                    page,
                    batchSize,
                    createdThisPage,
                    created,
                    Duration.between(pageStart, Instant.now())
            );

            page++;
        }

        return created;
    }

    private Optional<OldContactRepository.ContactView> lookupOld(String normalizedPhone) {
        Optional<OldContactRepository.ContactView> old = oldContactLookupService.searchByAnyPhone(normalizedPhone);

        if (old.isPresent()) {
            return old;
        }

        String spacedPhone = formatToSpacedNumber(normalizedPhone);
        if (spacedPhone != null) {
            return oldContactLookupService.searchByAnyPhone(spacedPhone);
        }

        return Optional.empty();
    }

    /**
     * Normalise un numéro FR en 10 chiffres commençant par 0.
     * Exemples gérés : "06 12 34 56 78", "+33 6 12 34 56 78", "00336..."
     * Retourne null si le numéro n'est pas exploitable.
     */
    private String normalizeFrenchPhone(String raw) {
        if (raw == null) return null;

        // Garder uniquement les chiffres
        String digits = raw.replaceAll("\\D+", "");
        if (digits.isEmpty()) return null;

        // 0033XXXXXXXXX -> 33XXXXXXXXX
        if (digits.startsWith("0033")) {
            digits = "33" + digits.substring(4);
        }

        // 33XXXXXXXXX (11 chiffres: 33 + 9) -> 0 + 9
        if (digits.startsWith("33") && digits.length() == 11) {
            digits = "0" + digits.substring(2);
        }

        // 9 chiffres (ex: 612345678) -> 0 + 9
        if (digits.length() == 9) {
            digits = "0" + digits;
        }

        // 10 chiffres attendus
        if (digits.length() != 10 || !digits.startsWith("0")) {
            return null;
        }

        return digits;
    }

    private String trimToNull(String s) {
        if (s == null) return null;
        String t = s.trim();
        return t.isEmpty() ? null : t;
    }

    /**
     * Formate un numéro normalisé (10 chiffres commençant par 0) en 0X XX XX XX XX.
     */
    private String formatToSpacedNumber(String normalized) {
        if (normalized == null || normalized.length() != 10 || !normalized.startsWith("0")) {
            return null;
        }
        return normalized.substring(0, 2) + " "
                + normalized.substring(2, 4) + " "
                + normalized.substring(4, 6) + " "
                + normalized.substring(6, 8) + " "
                + normalized.substring(8, 10);
    }
}
