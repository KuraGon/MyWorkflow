package com.saamp.workflow.service;

import com.saamp.workflow.entity.PhoneContactEntity;
import com.saamp.workflow.entity.PhoneLogEntity;
import com.saamp.workflow.entity.PhoneLogImportEntity;
import com.saamp.workflow.entity.PhoneUserEntity;
import com.saamp.workflow.mapper.*;
import com.saamp.workflow.repository.PhoneContactRepository;
import com.saamp.workflow.repository.PhoneLogImportRepository;
import com.saamp.workflow.repository.PhoneLogRepository;
import com.saamp.workflow.repository.PhoneUserRepository;
import com.workflow.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.HexFormat;
// java.util.* is imported below
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PhoneLogImportService {

    private final PhoneLogRepository phoneLogRepository;
    private final PhoneUserRepository phoneUserRepository;
    private final PhoneLogMapper phoneLogMapper;
    private final PhoneUserLiteMapper phoneUserLiteMapper;
    private final PhoneUserMapper phoneUserMapper;
    private final SalesRepStatsMapper salesRepStatsMapper;
    private final PhoneContactMapper phoneContactMapper;
    private final PageMetaMapper pageMetaMapper;
    private final PhoneContactRepository phoneContactRepository;
    private final PhoneLogImportRepository phoneLogImportRepository;
    ZoneOffset offset = ZoneOffset.UTC;

    private static final ConcurrentHashMap<String, Object> CHECKSUM_LOCKS = new ConcurrentHashMap<>();

    /**
     * Imports a list of phone log files, processing each file asynchronously.
     * This method blocks and waits for all asynchronous imports to complete before returning
     * the aggregated result containing the final statistics and errors.
     *
     * @param files The list of files to import.
     * @return A DTO summarizing the import process (file count, total rows, imported rows, errors).
     */
    public PhoneLogImportResultDTO importFiles(List<MultipartFile> files) {
        PhoneLogImportResultDTO result = new PhoneLogImportResultDTO();
        result.setFileCount(files != null ? files.size() : 0);
        result.setTotalRows(0L);
        result.setImportedRows(0L);

        if (files == null || files.isEmpty()) {
            result.addErrorsItem("Aucun fichier fourni");
            return result;
        }

        // ✅ dédoublonnage des fichiers identiques dans la même requête
        Map<String, MultipartFile> uniqueByChecksum = new LinkedHashMap<>();
        for (MultipartFile f : files) {
            try {
                String cs = computeChecksum(f);
                uniqueByChecksum.putIfAbsent(cs, f);
            } catch (Exception e) {
                result.addErrorsItem("Checksum impossible pour " + f.getOriginalFilename() + " : " + e.getMessage());
            }
        }

        List<Future<SingleFileImportStats>> futures = new ArrayList<>();

        for (MultipartFile file : uniqueByChecksum.values()) {
            try {
                futures.add(handleSingleFileImport(file));
            } catch (Exception e) {
                result.addErrorsItem("Erreur lors du lancement de l'importation pour "
                        + file.getOriginalFilename() + " : " + e.getMessage());
            }
        }

        for (Future<SingleFileImportStats> future : futures) {
            try {
                SingleFileImportStats stats = future.get();
                result.setTotalRows(result.getTotalRows() + stats.totalRows);
                result.setImportedRows(result.getImportedRows() + stats.importedRows);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                result.addErrorsItem("L'attente a été interrompue : " + e.getMessage());
            } catch (ExecutionException e) {
                result.addErrorsItem("Erreur lors de l'exécution d'un import : "
                        + (e.getCause() != null ? e.getCause().getMessage() : e.getMessage()));
            }
        }

        return result;
    }

    /**
     * Handles the import process for a single file asynchronously.
     * This method is executed in a separate thread and manages the transaction and
     * the history entry for the specific file.
     *
     * @param file The file to process.
     * @return A Future object containing the import statistics for the file.
     */
    @Async
    @Transactional
    public CompletableFuture<SingleFileImportStats> handleSingleFileImport(MultipartFile file) {
        String checksum = computeChecksum(file);
        Object lock = CHECKSUM_LOCKS.computeIfAbsent(checksum, k -> new Object());

        synchronized (lock) {
            try {
                long size = file.getSize();
                SingleFileImportStats stats = new SingleFileImportStats();
                PhoneLogImportEntity history = new PhoneLogImportEntity();
                boolean skipProcessing = false;

                // 1. Initial history check
                Optional<PhoneLogImportEntity> existing = phoneLogImportRepository.findByChecksum(checksum);

                if (existing.isPresent()) {
                    PhoneLogImportEntity prev = existing.get();

                    if ("SUCCESS".equals(prev.getStatus())) {
                        skipProcessing = true;
                    } else if ("IN_PROGRESS".equals(prev.getStatus())) {
                        skipProcessing = true;
                    } else {
                        // FAILED => reuse history line
                        history = prev;
                    }
                } else {
                    // New file => new history line
                    history = new PhoneLogImportEntity();
                }

                if (skipProcessing) {
                    // Return stats for the aggregation loop to continue without error
                    return CompletableFuture.completedFuture(stats);
                }

                // 2. Initialisation commune et marquage "IN_PROGRESS"
                history.setFileName(file.getOriginalFilename());
                history.setChecksum(checksum);
                history.setSizeBytes(size);
                history.setImportedAt(LocalDateTime.now());
                history.setStatus("IN_PROGRESS");
                history.setMessage(null);
                history = phoneLogImportRepository.save(history); // Save to mark start

                // 3. File processing and final status update
                try {
                    // Pass history entity to log line errors instead of the non-thread-safe global DTO
                    stats = processSingleFile(file, history);

                    if (stats.errorRows > 0) {
                        history.setStatus("FAILED");
                        // Message will be updated by processSingleFile with specific errors
                        history.setMessage("Échec. Lignes lues: " + stats.totalRows + ", Erreurs de parsing: " + stats.errorRows);
                    } else {
                        history.setStatus("SUCCESS");
                        history.setMessage("Import OK. Lignes importées: " + stats.importedRows);
                    }

                } catch (Exception e) {
                    history.setStatus("FAILED");
                    history.setMessage("Erreur globale lors de l'import : " + e.getMessage());
                    // Re-throw exception to be caught by the Future mechanism and reported in importFiles
                    throw new RuntimeException("Erreur lors de l'import de " + file.getOriginalFilename(), e);
                } finally {
                    phoneLogImportRepository.save(history); // Save final status
                }

                return CompletableFuture.completedFuture(stats);
            } finally {
                CHECKSUM_LOCKS.remove(checksum, lock);
            }
        }
    }

    /**
     * Processes the content of a single uploaded file.
     * It reads the file line by line, maps to entities, and saves them if no errors are found.
     * Errors are recorded in the provided history entity.
     *
     * @param file    The file content.
     * @param history The history entity to record line-level errors.
     * @return Statistics for the processed file.
     */
    private SingleFileImportStats processSingleFile(MultipartFile file, PhoneLogImportEntity history) {
        SingleFileImportStats stats = new SingleFileImportStats();

        List<PhoneLogEntity> buffer = new ArrayList<>();
        // Dé-duplication intra-fichier (soft)
        // On ne doit PAS fusionner des lignes juste parce que caller/dest + date + durée se ressemblent.
        // Exemple réel: certains exports contiennent 2 lignes (Emis/Recu) pour un même timestamp :
        // on veut garder les 2 lignes (elles peuvent avoir un sens différent selon la source),
        // et ne supprimer que les doublons stricts (même caller, même dest, même direction, etc.).
        Set<String> seenCallKeys = new HashSet<>();
        StringBuilder errorMessages = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {

            reader.readLine(); // skip header
            String line;
            long lineNumber = 1;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.isBlank()) continue;

                stats.totalRows++;

                try {
                    PhoneLogEntity log = mapLineToEntity(line);
                    String callKey = buildStrictCallKey(log);
                    if (callKey != null && !seenCallKeys.add(callKey)) {
                        // doublon intra-fichier : on ignore
                        continue;
                    }
                    buffer.add(log);
                } catch (Exception e) {
                    stats.errorRows++;
                    errorMessages.append("Fichier ").append(file.getOriginalFilename())
                            .append(" ligne ").append(lineNumber).append(" : ").append(e.getMessage()).append(". ");

                    // Logique client: échec du fichier à la première erreur
                    break;
                }
            }

            if (stats.errorRows > 0) {
                stats.importedRows = 0;
            } else {
                phoneLogRepository.saveAll(buffer);
                stats.importedRows = buffer.size();
            }

        } catch (Exception e) {
            stats.errorRows++;
            errorMessages.append("Erreur lors de la lecture du fichier ").append(file.getOriginalFilename())
                    .append(" : ").append(e.getMessage());
        }

        if (errorMessages.length() > 0) {
            String existingMsg = history.getMessage() != null ? history.getMessage() + "\n" : "";
            String newMsg = existingMsg + errorMessages.toString();
            history.setMessage(newMsg.substring(0, Math.min(newMsg.length(), 4000)));
        }

        return stats;
    }

    /**
     * Maps a CSV line to a PhoneLogEntity, using a semicolon (;) as the column separator.
     *
     * @param line The raw CSV line.
     * @return The populated PhoneLogEntity.
     * @throws IllegalArgumentException if the line format is invalid or parsing fails.
     */
    private PhoneLogEntity mapLineToEntity(String line) {
        PhoneLogEntity log = new PhoneLogEntity();

        String[] cols = line.split(";", -1);
        if (cols.length >= 11) {
            String forfaitMobile = cols[0].trim();
            String dateStr = cols[1].trim();
            String type = cols[2].trim();
            String direction = cols[3].trim();
            String appelant = cols[4].trim();
            String destination = cols[5].trim();
            String zoneClient = cols[6].trim();
            String callType = cols[7].trim();
            String durationStr = cols[8].trim();
            String quantityStr = cols[9].trim();
            String costStr = cols[10].trim();

            LocalDateTime eventDate = parseDateTime(dateStr);
            Integer durationSec = parseInteger(durationStr);
            BigDecimal quantityOctets = parseBigDecimal(quantityStr);
            BigDecimal costHt = parseBigDecimal(costStr);

            // --- Normalisation caller/destination ---
            // Beaucoup d'exports sont incohérents : parfois le "forfaitMobile" est dans la colonne destination
            // même quand la direction est "Emis" (ce qui fait doubler/faire exploser les stats).
            // On force donc :
            // - Emis   => caller = forfaitMobile, destination = autre partie
            // - Recu   => destination = forfaitMobile, caller = autre partie
            String forfait = normalizePhoneNumberOrNull(forfaitMobile);
            String a = normalizePhoneNumberOrNull(appelant);
            String d = normalizePhoneNumberOrNull(destination);

            String other = pickOtherParty(forfait, a, d);
            boolean isEmis = isEmitted(direction);
            boolean isRecu = isReceived(direction);

            String callerNum;
            String destNum;
            if (isEmis) {
                callerNum = forfait != null ? forfait : a;
                destNum = other != null ? other : d;
            } else if (isRecu) {
                callerNum = other != null ? other : a;
                destNum = forfait != null ? forfait : d;
            } else {
                // fallback : on ne sait pas => on garde la lecture brute
                callerNum = a;
                destNum = d;
            }

            PhoneContactEntity forfaitContact = getOrCreateContact(forfait);
            PhoneContactEntity callerContact = getOrCreateContact(callerNum);
            PhoneContactEntity destinationContact = getOrCreateContact(destNum);

            log.setEventDate(eventDate);
            log.setDirection(direction);
            log.setType(type);
            log.setZoneClient(zoneClient);
            log.setCallType(callType);
            log.setDurationSec(durationSec);
            log.setQuantityOctets(quantityOctets);
            log.setCostHt(costHt);

            log.setForfaitContact(forfaitContact);
            log.setCallerContact(callerContact);
            log.setDestinationContact(destinationContact);
        } else if (cols.length == 10) {
            String forfaitMobile = cols[0].trim();
            String dateStr = cols[1].trim();
            String type = cols[2].trim();
            String direction = cols[3].trim();
            String appelant = cols[4].trim();
            String destination = cols[5].trim();
            String zoneClient = cols[6].trim();
            String callType = cols[7].trim();
            String durationStr = cols[8].trim();
            String costStr = cols[9].trim();

            LocalDateTime eventDate = parseDateTime(dateStr);
            Integer durationSec = parseInteger(durationStr);
            BigDecimal costHt = parseBigDecimal(costStr);

            // --- Normalisation caller/destination (voir commentaire plus haut) ---
            String forfait = normalizePhoneNumberOrNull(forfaitMobile);
            String a = normalizePhoneNumberOrNull(appelant);
            String d = normalizePhoneNumberOrNull(destination);

            String other = pickOtherParty(forfait, a, d);
            boolean isEmis = isEmitted(direction);
            boolean isRecu = isReceived(direction);

            String callerNum;
            String destNum;
            if (isEmis) {
                callerNum = forfait != null ? forfait : a;
                destNum = other != null ? other : d;
            } else if (isRecu) {
                callerNum = other != null ? other : a;
                destNum = forfait != null ? forfait : d;
            } else {
                callerNum = a;
                destNum = d;
            }

            PhoneContactEntity forfaitContact = getOrCreateContact(forfait);
            PhoneContactEntity callerContact = getOrCreateContact(callerNum);
            PhoneContactEntity destinationContact = getOrCreateContact(destNum);

            log.setEventDate(eventDate);
            log.setDirection(direction);
            log.setType(type);
            log.setZoneClient(zoneClient);
            log.setCallType(callType);
            log.setDurationSec(durationSec);
            log.setCostHt(costHt);

            log.setForfaitContact(forfaitContact);
            log.setCallerContact(callerContact);
            log.setDestinationContact(destinationContact);
        } else if (cols.length == 5) {
            if (!cols[0].trim().equals("Extension")) {
                String extension = cols[0].trim();
                String equivalent = cols[1].trim();
                String dateStr = cols[2].trim();
                String durationStr = cols[3].trim();
                String telavoxType = cols[4].trim();

                LocalDateTime eventDate = parseDateTime(dateStr);
                Integer durationSec = parseInteger(durationStr);

                // Par défaut, on considère "Extension" comme notre numéro (forfait)
                String direction = null;
                String type = "APPEL";
                String callType = null;

                String normalizedTelType = telavoxType.toLowerCase(Locale.ROOT);

                // Heuristique direction + mapping caller/destination
                String caller;
                String destination;

                if (normalizedTelType.contains("sortant") || normalizedTelType.contains("outgoing")) {
                    direction = "Emis";
                    caller = extension;
                    destination = equivalent;
                } else if (normalizedTelType.contains("entrant") || normalizedTelType.contains("incoming")) {
                    direction = "Recu";
                    caller = equivalent;
                    destination = extension;
                } else if (normalizedTelType.contains("manqu") || normalizedTelType.contains("missed")) {
                    direction = "Recu";
                    callType = "MANQUE";
                    caller = equivalent;
                    destination = extension;
                } else {
                    callType = telavoxType;
                    caller = extension;
                    destination = equivalent;
                }

                PhoneContactEntity forfaitContact = getOrCreateContact(extension);
                PhoneContactEntity callerContact = getOrCreateContact(caller);
                PhoneContactEntity destinationContact = getOrCreateContact(destination);

                log.setEventDate(eventDate);
                log.setDirection(direction);
                log.setType(type);
                log.setZoneClient(null);
                log.setCallType(callType);
                log.setDurationSec(durationSec);
                log.setQuantityOctets(null);
                log.setCostHt(null);

                log.setForfaitContact(forfaitContact);
                log.setCallerContact(callerContact);
                log.setDestinationContact(destinationContact);
            }
        }
        return log;
    }

    private static final DateTimeFormatter[] DATE_FORMATS = {
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"),
            // Telavox exports
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    };

    private LocalDateTime parseDateTime(String value) {
        if (value == null || value.isBlank()) return null;

        value = value.replace("\"", "").trim();

        for (DateTimeFormatter fmt : DATE_FORMATS) {
            try {
                return LocalDateTime.parse(value, fmt);
            } catch (Exception ignored) {
            }
        }

        throw new IllegalArgumentException("Impossible de parser la date: " + value);
    }

    private Integer parseInteger(String value) {
        if (value == null || value.isBlank()) return null;
        return Integer.parseInt(value);
    }

    private Long parseLong(String value) {
        if (value == null || value.isBlank()) return null;
        return Long.parseLong(value);
    }

    private BigDecimal parseBigDecimal(String value) {
        if (value.isBlank() || value.equals("-")) {
            return null;
        }

        value = value.trim()
                .replace("\"", "")
                .replace(",", ".");

        return new BigDecimal(value);
    }


    private PhoneContactEntity getOrCreateContact(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            return null;
        }

        String normalized = normalizePhoneNumber(phoneNumber);

        return phoneContactRepository.findByPhoneNumber(normalized)
                .orElseGet(() -> {
                    PhoneContactEntity contact = new PhoneContactEntity();
                    contact.setPhoneNumber(normalized);
                    return phoneContactRepository.save(contact);
                });
    }


    private String normalizePhoneNumber(String raw) {
        if (raw == null) return null;

        String cleaned = raw.trim();

        // Certains exports Excel sortent des numéros au format scientifique (ex: 3,3478E+10).
        // Dans ce cas, on tente de reconstruire une représentation "plain" avant de ne garder que les chiffres.
        if (cleaned.matches(".*[eE].*")) {
            try {
                String numeric = cleaned.replace(" ", "").replace(",", ".");
                java.math.BigDecimal bd = new java.math.BigDecimal(numeric);
                cleaned = bd.toPlainString();
                // 123.0 -> 123
                if (cleaned.endsWith(".0")) {
                    cleaned = cleaned.substring(0, cleaned.length() - 2);
                }
            } catch (Exception ignore) {
                // On retombe sur le parsing classique
            }
        }

        String p = cleaned.replaceAll("[^0-9+]", "");

        // FR explicite
        if (p.startsWith("0033")) {
            p = "0" + p.substring(4);
        } else if (p.startsWith("+33")) {
            p = "0" + p.substring(3);
        }
        // FR sans '+' (ex: 33612345678) => 11 chars attendus
        else if (p.startsWith("33") && p.length() == 11) {
            p = "0" + p.substring(2);
        }
        // Préfixe international générique (CSV met 00 partout)
        else if (p.startsWith("00")) {
            p = p.substring(2);
        }

        return p;
    }

    private String normalizePhoneNumberOrNull(String raw) {
        if (raw == null || raw.isBlank()) {
            return null;
        }
        String n = normalizePhoneNumber(raw);
        return (n == null || n.isBlank()) ? null : n;
    }

    private boolean isOutgoing(String direction) {
        if (direction == null) return false;
        String d = direction.trim().toLowerCase(Locale.ROOT);
        return d.contains("emis") || d.contains("sort") || d.contains("out");
    }

    private boolean isIncoming(String direction) {
        if (direction == null) return false;
        String d = direction.trim().toLowerCase(Locale.ROOT);
        return d.contains("recu") || d.contains("entrant") || d.contains("in");
    }

    /**
     * Détermine "l'autre partie" d'un appel à partir des champs 'appelant' et 'destination' en écartant
     * le numéro forfait (si présent).
     */
    private String pickOtherParty(String normalizedForfait, String normalizedAppelant, String normalizedDestination) {
        if (normalizedAppelant != null && !normalizedAppelant.equals(normalizedForfait)) {
            return normalizedAppelant;
        }
        if (normalizedDestination != null && !normalizedDestination.equals(normalizedForfait)) {
            return normalizedDestination;
        }
        return normalizedAppelant != null ? normalizedAppelant : normalizedDestination;
    }

    /**
     * Clé "stricte" d'un appel : dé-duplication uniquement des vrais doublons (même ligne répétée).
     *
     * IMPORTANT :
     * - on NE trie PAS caller/destination (la direction compte)
     * - on inclut la direction (Emis/Recu)
     *
     * Ça évite de faire disparaître des lignes valides quand un export contient à la fois une ligne "Emis" et
     * une ligne "Recu" pour un même couple / timestamp.
     */
    private String buildStrictCallKey(PhoneLogEntity log) {
        if (log == null || log.getCallerContact() == null || log.getDestinationContact() == null || log.getEventDate() == null) {
            return null;
        }

        String callerId = log.getCallerContact().getId().toString();
        String destId = log.getDestinationContact().getId().toString();

        String type = log.getType() == null ? "" : log.getType().trim().toUpperCase(Locale.ROOT);
        String direction = log.getDirection() == null ? "" : log.getDirection().trim().toUpperCase(Locale.ROOT);
        String callType = log.getCallType() == null ? "" : log.getCallType().trim().toUpperCase(Locale.ROOT);
        Integer duration = log.getDurationSec() == null ? 0 : log.getDurationSec();
        String cost = log.getCostHt() == null ? "" : log.getCostHt().toPlainString();
        String octets = log.getQuantityOctets() == null ? "" : log.getQuantityOctets().toPlainString();

        return callerId + "|" + destId + "|" + log.getEventDate().toString() + "|" + type + "|" + direction + "|" + callType + "|" + duration + "|" + cost + "|" + octets;
    }


    private String computeChecksum(MultipartFile file) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(file.getBytes());
            return HexFormat.of().formatHex(digest);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors du calcul du checksum du fichier " + file.getOriginalFilename(), e);
        }
    }

    /**
     * Retrieves the latest import history records.
     *
     * @param limit The maximum number of records to return.
     * @return A list of import history DTOs.
     */
    @Transactional(readOnly = true)
    public List<PhoneLogImportHistoryDTO> getLatestImports(Integer limit) {
        int effectiveLimit = (limit == null || limit < 1 || limit > 100) ? 10 : limit;

        return phoneLogImportRepository
                .findAll(PageRequest.of(0, effectiveLimit, Sort.by(Sort.Direction.DESC, "importedAt")))
                .stream()
                .map(this::toHistoryDto)
                .toList();
    }

    private PhoneLogImportHistoryDTO toHistoryDto(PhoneLogImportEntity entity) {

        PhoneLogImportHistoryDTO dto = new PhoneLogImportHistoryDTO();
        dto.setId(entity.getId());
        dto.setFileName(entity.getFileName());
        dto.setChecksum(entity.getChecksum());
        dto.setSizeBytes(entity.getSizeBytes());
        dto.setImportedAt(entity.getImportedAt().atOffset(offset));
        dto.setStatus(entity.getStatus());
        dto.setMessage(entity.getMessage());
        return dto;
    }

    // --------------- PARTIE LECTURE / HISTORIQUE ---------------- //

    /**
     * Retrieves a list of distinct phone contacts associated with mobile plans.
     *
     * @return A list of phone contact DTOs.
     */
    @Transactional(readOnly = true)
    public List<PhoneUserDTO> getPhoneContacts() {
        List<PhoneUserEntity> entities =
                phoneLogRepository.findDistinctForfaitUsers();

        return entities.stream()
                .map(phoneUserMapper::toDto)
                .toList();
    }

    private Map<UUID, PhoneUserEntity> resolveUsersByContacts(
            List<PhoneLogEntity> entities
    ) {
        // 1. Collecte des contactIds présents dans les logs
        Set<UUID> contactIds = entities.stream()
                .flatMap(e -> Stream.of(
                        e.getCallerContact(),
                        e.getDestinationContact()
                ))
                .filter(Objects::nonNull)
                .map(PhoneContactEntity::getId)
                .collect(Collectors.toSet());

        if (contactIds.isEmpty()) {
            return Map.of();
        }

        // 2. Chargement des users liés à ces contacts (UNE requête)
        List<PhoneUserEntity> users =
                phoneUserRepository.findAllByAnyPhoneContactIdIn(contactIds);

        // 3. Index contactId -> user
        Map<UUID, PhoneUserEntity> result = new HashMap<>();

        for (PhoneUserEntity user : users) {
            Stream.of(
                            user.getPhonePro(),
                            user.getPhoneOther(),
                            user.getPhonePerso(),
                            user.getPhoneTelavox()
                    )
                    .filter(Objects::nonNull)
                    .forEach(contact -> result.put(contact.getId(), user));
        }

        return result;
    }

    @Transactional(readOnly = true)
    public PhoneLogPageDTO getPhoneLogsPage(
            UUID userId,
            OffsetDateTime startDate,
            OffsetDateTime endDate,
            Integer page,
            Integer size
    ) {

        int p = (page == null || page < 0) ? 0 : page;
        int s = (size == null || size < 1 || size > 200) ? 50 : size;

        PageRequest pr = PageRequest.of(p, s, Sort.by(Sort.Direction.DESC, "eventDate"));

        // 1. Récupération du user
        PhoneUserEntity user = phoneUserRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("PhoneUser not found"));

        // 2. Résolution user → contacts
        List<UUID> contactIds = Stream.of(
                        user.getPhonePro(),
                        user.getPhoneOther(),
                        user.getPhoneTelavox(),
                        user.getPhonePerso()
                )
                .filter(Objects::nonNull)
                .map(PhoneContactEntity::getId)
                .toList();

        if (contactIds.isEmpty()) {
            return pageMetaMapper.toDTO(Page.empty(pr));
        }

        // 3. Recherche paginée
        Page<PhoneLogEntity> entityPage;

        if (startDate != null && endDate != null) {
            entityPage = phoneLogRepository.findByForfaitContactIdInAndEventDateBetween(
                    contactIds,
                    startDate.toLocalDateTime(),
                    endDate.toLocalDateTime(),
                    pr
            );
        } else {
            entityPage = phoneLogRepository.findByForfaitContactIdIn(contactIds, pr);
        }

        if (entityPage.isEmpty()) {
            return pageMetaMapper.toDTO(entityPage.map(e -> null));
        }

        // 4. Résolution contact → user (UNIQUEMENT pour la page)
        List<PhoneLogEntity> entities = entityPage.getContent();

        Map<UUID, PhoneUserEntity> userByContactId =
                resolveUsersByContacts(entities);

        // 5. Mapping enrichi
        Page<PhoneLogDTO> dtoPage = entityPage.map(entity -> {

            PhoneContactEntity callerContact = entity.getCallerContact();
            PhoneContactEntity destContact = entity.getDestinationContact();

            PhoneUserEntity callerUser =
                    callerContact != null ? userByContactId.get(callerContact.getId()) : null;
            PhoneUserEntity destUser =
                    destContact != null ? userByContactId.get(destContact.getId()) : null;

            PhoneLogDTO dto = phoneLogMapper.toDto(entity);

            dto.setAppelant(
                    callerUser != null
                            ? phoneUserLiteMapper.toLiteDto(callerUser, callerContact)
                            : buildExternalPhoneUserLite(callerContact)
            );

            dto.setDestination(
                    destUser != null
                            ? phoneUserLiteMapper.toLiteDto(destUser, destContact)
                            : buildExternalPhoneUserLite(destContact)
            );
            return dto;
        });

        // 6. Mapping page → DTO page (inchangé)
        return pageMetaMapper.toPageDTO(dtoPage);
    }

    private PhoneUserLiteDTO buildExternalPhoneUserLite(PhoneContactEntity contact) {
        if (contact == null) {
            return null;
        }

        PhoneUserLiteDTO dto = new PhoneUserLiteDTO();
        dto.setId(null);
        dto.setFirstname(null);
        dto.setLastname(null);
        dto.setDenomination(null);
        dto.setCompany(null);
        dto.setPhone(phoneContactMapper.toDto(contact));
        return dto;
    }

    private static class SingleFileImportStats {
        long totalRows = 0;
        long importedRows = 0;
        long errorRows = 0;
    }

    // ------------------ DASHBOARD STATS ------------------ //

    /**
     * Calcule les KPI globaux + top commerciaux + top clients
     * en fonction des filtres de l'API /phone-logs/dashboard-stats.
     */
    public DashboardStatsDTO getDashboardStats(
            OffsetDateTime startDate,
            OffsetDateTime endDate,
            UUID commercialId,
            String type
    ) {
        // 1. On récupère tous les logs (version simple, à optimiser si volume énorme)
        List<PhoneLogEntity> allLogs = phoneLogRepository.findAll();

        // 2. On applique les filtres en mémoire
        LocalDateTime start = startDate != null ? startDate.toLocalDateTime() : null;
        LocalDateTime end = endDate != null ? endDate.toLocalDateTime() : null;

        String normalizedType = (type == null || type.isBlank()) ? "ALL" : type.toUpperCase(Locale.ROOT);

        List<PhoneLogEntity> filtered = allLogs.stream()
                // filtre dates
                .filter(log -> {
                    LocalDateTime eventDate = log.getEventDate();
                    if (eventDate == null) return false;
                    if (start != null && eventDate.isBefore(start)) return false;
                    return end == null || !eventDate.isAfter(end);
                })
                // filtre commercial (forfaitContact)
                .filter(log -> commercialId == null
                        || (log.getForfaitContact() != null
                        && commercialId.equals(log.getForfaitContact().getId())))
                .toList();

        // 3. On construit l'objet de réponse
        DashboardStatsDTO dashboard = new DashboardStatsDTO();
        dashboard.setKpi(buildGlobalKpi(filtered));
        dashboard.setTopCommercials(buildTopCommercials(filtered));
        dashboard.setTopClients(buildTopClients(filtered));
        return dashboard;
    }

    @Transactional(readOnly = true)
    public SalesRepStatsDTO getSalesRepStats(
            UUID userId,
            OffsetDateTime startDate,
            OffsetDateTime endDate
    ) {
        if (userId == null) {
            throw new IllegalArgumentException("userId est requis");
        }

        LocalDateTime start = startDate != null ? startDate.toLocalDateTime() : null;
        LocalDateTime end = endDate != null ? endDate.toLocalDateTime() : null;

        // 1. Récupération du user
        PhoneUserEntity user = phoneUserRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("PhoneUser not found"));

        // 2. Résolution user → contacts
        List<UUID> contactIds = Stream.of(
                        user.getPhonePro(),
                        user.getPhoneOther(),
                        user.getPhoneTelavox(),
                        user.getPhonePerso()
                )
                .filter(Objects::nonNull)
                .map(PhoneContactEntity::getId)
                .toList();

        if (contactIds.isEmpty()) {
            return new SalesRepStatsDTO();
        }

        // 3. Appels repository (multi-numéros)
        PhoneLogRepository.SalesRepKpiProjection kpiAgg =
                phoneLogRepository.computeSalesRepKpis(contactIds, start, end);

        List<PhoneLogRepository.SalesRepHourActivityProjection> hoursAgg =
                phoneLogRepository.computeSalesRepHourActivitySplit(contactIds, start, end);

        // 4. Mapping DTO
        SalesRepKpiDTO kpi = salesRepStatsMapper.toKpiDto(kpiAgg);

        long totalInteractions = nvl(kpiAgg.getTotalInteractions());
        long clientInteractions = nvl(kpiAgg.getClientInteractions());
        long internalInteractions = nvl(kpiAgg.getInternalInteractions());
        long unknownInteractions = nvl(kpiAgg.getUnknownInteractions());

        kpi.setRatio(
                buildRatio(
                        totalInteractions,
                        clientInteractions,
                        internalInteractions,
                        unknownInteractions
                )
        );

        List<SalesRepHourBucketDTO> activityByHour = hoursAgg.stream()
                .map(salesRepStatsMapper::toHourBucket)
                .toList();

        SalesRepStatsDTO dto = new SalesRepStatsDTO();
        dto.setCommercialId(userId);
        dto.setStartDate(startDate);
        dto.setEndDate(endDate);
        dto.setKpi(kpi);
        dto.setActivityByHour(activityByHour);

        return dto;
    }


    private long nvl(Long v) {
        return v == null ? 0L : v;
    }

    private SalesRepRatioDTO buildRatio(long total, long client, long internal, long unknown) {
        SalesRepRatioDTO ratio = new SalesRepRatioDTO();

        if (total <= 0) {
            ratio.setClientPct(0.0);
            ratio.setInternalPct(0.0);
            ratio.setUnknownPct(0.0);
            return ratio;
        }

        ratio.setClientPct((double) Math.round((client * 100.0) / total));
        ratio.setInternalPct((double) Math.round((internal * 100.0) / total));
        ratio.setUnknownPct((double) Math.round((unknown * 100.0) / total));

        // optionnel : ajuste le dernier pour faire 100 pile
        int sum = (int) (ratio.getClientPct() + ratio.getInternalPct() + ratio.getUnknownPct());
        if (sum != 100) {
            ratio.setUnknownPct(ratio.getUnknownPct() + (100 - sum));
        }

        return ratio;
    }

    private GlobalKpiDTO buildGlobalKpi(List<PhoneLogEntity> logs) {
        GlobalKpiDTO kpi = new GlobalKpiDTO();

        long totalCalls = logs.stream()
                .filter(l -> "VOIX".equalsIgnoreCase(l.getType()))
                .count();

        long totalSms = logs.stream()
                .filter(l -> "SMS".equalsIgnoreCase(l.getType()))
                .count();

        long totalDurationSec = logs.stream()
                .filter(l -> "VOIX".equalsIgnoreCase(l.getType()))
                .map(PhoneLogEntity::getDurationSec)
                .filter(Objects::nonNull)
                .mapToLong(Integer::intValue)
                .sum();

        long uniqueContacts = logs.stream()
                .map(this::extractDestinationPhoneNumber)
                .filter(Objects::nonNull)
                .distinct()
                .count();

        long totalCallWeekendAndNight = logs.stream()
                .filter(l -> "VOIX".equalsIgnoreCase(l.getType()))
                .filter(l -> l.getEventDate() != null)
                .filter(l -> {
                    LocalDateTime dt = l.getEventDate();
                    DayOfWeek day = dt.getDayOfWeek();
                    int hour = dt.getHour();

                    boolean isWeekend =
                            day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;

                    boolean isNight =
                            hour < 7 || hour >= 21;

                    return isWeekend || isNight;
                })
                .count();

        kpi.setTotalCallsWeekEndAndNight(totalCallWeekendAndNight);
        kpi.setTotalCalls(totalCalls);
        kpi.setTotalSms(totalSms);
        kpi.setTotalDurationSec(totalDurationSec);
        kpi.setUniqueContactsCount(uniqueContacts);

        return kpi;
    }

    private List<TopCommercialStatsDTO> buildTopCommercials(List<PhoneLogEntity> logs) {
        // Groupement par forfait (le mobile du commercial)
        Map<UUID, List<PhoneLogEntity>> byForfait = logs.stream()
                .filter(l -> l.getForfaitContact() != null)
                .collect(Collectors.groupingBy(l -> l.getForfaitContact().getId()));

        List<TopCommercialStatsDTO> result = new ArrayList<>();

        for (Map.Entry<UUID, List<PhoneLogEntity>> entry : byForfait.entrySet()) {
            List<PhoneLogEntity> commercialLogs = entry.getValue();
            PhoneContactEntity contact = commercialLogs.get(0).getForfaitContact();

            long nbCalls = commercialLogs.stream()
                    .filter(l -> "VOIX".equalsIgnoreCase(l.getType()))
                    .count();

            long nbSms = commercialLogs.stream()
                    .filter(l -> "SMS".equalsIgnoreCase(l.getType()))
                    .count();

            long totalDuration = commercialLogs.stream()
                    .filter(l -> "VOIX".equalsIgnoreCase(l.getType()))
                    .map(PhoneLogEntity::getDurationSec)
                    .filter(Objects::nonNull)
                    .mapToLong(Integer::intValue)
                    .sum();

            TopCommercialStatsDTO dto = new TopCommercialStatsDTO();
            dto.setName(buildCommercialName(contact));
            dto.setNbCalls(nbCalls);
            dto.setNbSms(nbSms);
            dto.setTotalDuration(totalDuration);

            result.add(dto);
        }

        // Tri par nombre total d'échanges (appels + SMS), décroissant, on garde les 10 premiers
        return result.stream()
                .sorted(Comparator.comparingLong(
                                (TopCommercialStatsDTO c) ->
                                        Optional.ofNullable(c.getNbCalls()).orElse(0L)
                                                + Optional.ofNullable(c.getNbSms()).orElse(0L)
                        ).reversed()
                )
                .limit(10)
                .toList();
    }

    private List<TopClientStatsDTO> buildTopClients(List<PhoneLogEntity> logs) {
        // Groupement par numéro de téléphone du client/prospect
        Map<String, List<PhoneLogEntity>> byClient = logs.stream()
                .collect(Collectors.groupingBy(this::extractDestinationPhoneNumberSafe));

        List<TopClientStatsDTO> result = new ArrayList<>();

        for (Map.Entry<String, List<PhoneLogEntity>> entry : byClient.entrySet()) {
            String phoneNumber = entry.getKey();
            if (phoneNumber == null) {
                continue;
            }

            List<PhoneLogEntity> clientLogs = entry.getValue();

            TopClientStatsDTO dto = new TopClientStatsDTO();
            dto.setPhoneNumber(phoneNumber);
            dto.setNbExchanges((long) clientLogs.size());

            OffsetDateTime lastInteraction = clientLogs.stream()
                    .map(PhoneLogEntity::getEventDate)
                    .filter(Objects::nonNull)
                    .map(d -> d.atOffset(offset))
                    .max(Comparator.naturalOrder())
                    .orElse(null);

            dto.setLastInteraction(lastInteraction);
            result.add(dto);
        }

        // Tri par nb d'échanges décroissant, on garde les 10 premiers
        return result.stream()
                .sorted(Comparator.comparingLong(
                                (TopClientStatsDTO c) ->
                                        Optional.ofNullable(c.getNbExchanges()).orElse(0L)
                        ).reversed()
                )
                .limit(10)
                .toList();
    }

    private String extractDestinationPhoneNumber(PhoneLogEntity entity) {
        if (entity.getDestinationContact() != null) {
            return entity.getDestinationContact().getPhoneNumber();
        }
        // si tu as un champ "destination" brut dans l'entity, tu peux le renvoyer ici
        return null;
    }

    private String extractDestinationPhoneNumberSafe(PhoneLogEntity entity) {
        return extractDestinationPhoneNumber(entity);
    }

    private String buildCommercialName(PhoneContactEntity contact) {
        if (contact == null) {
            return "N/A";
        }
        /*
        String fullName = (
                (contact.getFirstname() != null ? contact.getFirstname() + " " : "") +
                        (contact.getLastname() != null ? contact.getLastname() : "")
        ).trim();

        if (!fullName.isEmpty()) {
            return fullName;
        }

        if (contact.getLabel() != null && !contact.getLabel().isBlank()) {
            return contact.getLabel();
        }
        */
        return contact.getPhoneNumber();
    }

    /**
     * Certains exports (ou traitements) manipulent un champ "direction" hétérogène.
     * On centralise ici une normalisation minimale pour éviter de dupliquer les heuristiques.
     */
    private static boolean isEmitted(String direction) {
        if (direction == null) return false;
        String d = java.text.Normalizer
                .normalize(direction, java.text.Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .trim()
                .toUpperCase(java.util.Locale.ROOT);
        return d.contains("EMIS") || d.contains("SORTANT") || d.contains("OUTGOING");
    }

    private static boolean isReceived(String direction) {
        if (direction == null) return false;
        String d = java.text.Normalizer
                .normalize(direction, java.text.Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .trim()
                .toUpperCase(java.util.Locale.ROOT);
        return d.contains("RECU") || d.contains("ENTRANT") || d.contains("INCOMING");
    }

}