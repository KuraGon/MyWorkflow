package com.saamp.workflow.service;

import com.saamp.workflow.entity.PhoneContactEntity;
import com.saamp.workflow.entity.PhoneUserEntity;
import com.saamp.workflow.mapper.PhoneLogStatsMapper;
import com.saamp.workflow.repository.PhoneLogRepository;
import com.saamp.workflow.repository.PhoneUserRepository;
import com.workflow.dto.CorrespondentStatsResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PhoneLogStatsService {

    private final PhoneLogRepository phoneLogRepository;
    private final PhoneUserRepository phoneUserRepository;
    private final PhoneLogStatsMapper phoneLogStatsMapper;

    @Transactional(readOnly = true)
    public CorrespondentStatsResponseDTO getCorrespondentStats(
            UUID userId,
            OffsetDateTime startDate,
            OffsetDateTime endDate,
            Integer limitCalls,
            Integer limitSms
    ) {

        int callsLimit = (limitCalls == null || limitCalls < 1 || limitCalls > 500) ? 50 : limitCalls;
        int smsLimit   = (limitSms   == null || limitSms   < 1 || limitSms   > 500) ? 50 : limitSms;

        LocalDateTime start = startDate != null ? startDate.toLocalDateTime() : null;
        LocalDateTime end   = endDate   != null ? endDate.toLocalDateTime()   : null;

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
            return new CorrespondentStatsResponseDTO();
        }

        UUID[] contactIdsArray = contactIds.stream().toArray(UUID[]::new);

        // 3. Appels repository
        var callRows = phoneLogRepository
                .computeGlobalCallStats(contactIdsArray, start, end);

        var smsRows = phoneLogRepository
                .computeGlobalSmsStats(contactIdsArray, start, end);

        // 4. Mapping DTO
        CorrespondentStatsResponseDTO res = new CorrespondentStatsResponseDTO();
        res.setCallStats(
                callRows.stream()
                        .limit(callsLimit)
                        .map(phoneLogStatsMapper::toCallDto)
                        .toList()
        );
        res.setSmsStats(
                smsRows.stream()
                        .limit(smsLimit)
                        .map(phoneLogStatsMapper::toSmsDto)
                        .toList()
        );

        return res;
    }
}
