package com.saamp.workflow.service;

import com.saamp.olddb.repository.StatistiqueRepository;
import com.saamp.workflow.entity.PhoneUserEntity;
import com.saamp.workflow.repository.PhoneUserRepository;
import com.workflow.dto.PhoneUserMonthlyCaHmDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class PhoneUserStatsService {

    private final PhoneUserRepository phoneUserRepository;
    private final StatistiqueRepository statistiqueRepository;

    public PhoneUserStatsService(PhoneUserRepository phoneUserRepository,
                                 StatistiqueRepository statistiqueRepository) {
        this.phoneUserRepository = phoneUserRepository;
        this.statistiqueRepository = statistiqueRepository;
    }

    public PhoneUserMonthlyCaHmDTO getMonthlyCaHmForPhoneUser(
            UUID phoneUserId,
            int annee,
            int mois
    ) {

        // 1) Charger le phone user
        PhoneUserEntity user = phoneUserRepository
                .findByIdAndMysaampIdClientIsNotNull(phoneUserId)
                .orElseThrow(() ->
                        new IllegalArgumentException("PhoneUser not found or not linked to a MySAAMP client: " + phoneUserId)
                );

        Long clIdent = user.getMysaampIdClient();

        // 2) Récupérer les stats agrégées du mois (old db)
        BigDecimal caHm;
        try {
            caHm = statistiqueRepository
                    .sumCaHmByClient(annee, mois)
                    .stream()
                    .filter(a -> clIdent.equals(a.getClIdent()))
                    .map(a -> a.getCaHm() == null ? BigDecimal.ZERO : a.getCaHm())
                    .findFirst()
                    .orElse(BigDecimal.ZERO);
        } catch (DataAccessException ex) {
            // La base historique MySQL n'est pas indispensable au fonctionnement
            // de l'écran téléphonie. En environnement local, on laisse l'écran
            // continuer à fonctionner avec un CA/HM à 0 si olddb est indisponible.
            log.warn(
                    "Base olddb indisponible pour phoneUserId={} ({}-{}). CA/HM forcé à 0.",
                    phoneUserId, annee, mois, ex
            );
            caHm = BigDecimal.ZERO;
        }

        // 3) Mapper vers le DTO
        PhoneUserMonthlyCaHmDTO dto = new PhoneUserMonthlyCaHmDTO();
        dto.setAnnee(annee);
        dto.setMois(mois);
        dto.setCaHm(caHm.doubleValue());
        dto.setPhoneUserId(user.getId());
        dto.setMysaampIdClient(user.getMysaampIdClient());
        dto.setLabel(buildLabel(user));
        return dto;
    }


    private String buildLabel(PhoneUserEntity u) {
        if (u.getFirstname() != null || u.getLastname() != null) {
            return (safe(u.getFirstname()) + " " + safe(u.getLastname())).trim();
        }
        if (u.getDenomination() != null) return u.getDenomination();
        if (u.getCompany() != null) return u.getCompany();
        return "client " + u.getMysaampIdClient();
    }

    private String safe(String s) {
        return s == null ? "" : s;
    }
}
