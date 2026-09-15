package com.saamp.workflow.controller;

import com.saamp.workflow.service.*;
import com.workflow.api.PhoneLogsApi;
import com.workflow.dto.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "basicAuth")
public class PhoneLogsApiController implements PhoneLogsApi {

    private final PhoneLogImportService phoneLogImportService;
    private final PhoneUsersService phoneUsersService;
    private final PhoneUserStatsService phoneUserStatsService;
    private final PhoneLogStatsService phoneLogStatsService;
    private final PhoneContactService phoneContactService;

    @Override
    public ResponseEntity<PhoneLogImportResultDTO> importPhoneLogs(List<MultipartFile> files) {
        PhoneLogImportResultDTO result = phoneLogImportService.importFiles(files);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<List<PhoneLogImportHistoryDTO>> getPhoneLogImports(Integer limit) {
        List<PhoneLogImportHistoryDTO> history = phoneLogImportService.getLatestImports(limit);
        return ResponseEntity.ok(history);
    }

    @Override
    public ResponseEntity<List<PhoneUserDTO>> getPhoneContacts() {
        List<PhoneUserDTO> contacts = phoneLogImportService.getPhoneContacts();
        return ResponseEntity.ok(contacts);
    }

    @Override
    public ResponseEntity<PhoneUserDTO> getPhoneUsersDetail(
            @PathVariable("phoneUserId") UUID phoneUserId
    ) {
        return ResponseEntity.ok(phoneUsersService.getUserDetail(phoneUserId));
    }

    @Override
    public ResponseEntity<PhoneUserPageDTO> getPhoneUsersPage(Integer page, Integer size, String q) {
        PhoneUserPageDTO dtos = phoneUsersService.getPhoneUsersPage(page, size, q, null);
        return ResponseEntity.ok(dtos);
    }

    @Override
    public ResponseEntity<PhoneUserPageDTO> getPhoneUsersPageWithClIdent(
            @PathVariable("mysaampIdClient") Long mysaampIdClient,
            @Min(0)  @Valid @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @Min(1) @Max(200)  @Valid @RequestParam(value = "size", required = false, defaultValue = "25") Integer size,
            @Valid @RequestParam(value = "q", required = false) String q
    ) {    PhoneUserPageDTO dtos = phoneUsersService.getPhoneUsersPage(page, size, q, mysaampIdClient);
        return ResponseEntity.ok(dtos);
    }

    @Override
    public ResponseEntity<DashboardStatsDTO> getDashboardStats(
            OffsetDateTime startDate,
            OffsetDateTime endDate,
            UUID commercialId,
            String type
    ) {
        DashboardStatsDTO stats =
                phoneLogImportService.getDashboardStats(startDate, endDate, commercialId, type);
        return ResponseEntity.ok(stats);
    }

    @Override
    public ResponseEntity<PhoneLogPageDTO> getPhoneLogsPage(
            UUID userId,
            OffsetDateTime startDate,
            OffsetDateTime endDate,
            Integer page,
            Integer size
    ) {
        PhoneLogPageDTO dto = phoneLogImportService.getPhoneLogsPage(
                userId, startDate, endDate, page, size
        );
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<CorrespondentStatsResponseDTO> getCorrespondentStats(
            UUID userId,
            OffsetDateTime startDate,
            OffsetDateTime endDate,
            Integer limitCalls,
            Integer limitSms
    ) {
        CorrespondentStatsResponseDTO res = phoneLogStatsService.getCorrespondentStats(
                userId, startDate, endDate, limitCalls, limitSms
        );
        return ResponseEntity.ok(res);
    }

    @Override
    public ResponseEntity<SalesRepStatsDTO> getSalesRepStats(UUID userId, OffsetDateTime startDate, OffsetDateTime endDate
    ) {
        SalesRepStatsDTO stats = phoneLogImportService.getSalesRepStats(userId, startDate, endDate);
        return ResponseEntity.ok(stats);
    }

    @Override
    public ResponseEntity<PhoneUserMonthlyCaHmDTO> getPhoneUserStats(UUID phoneUserId, Integer annee, Integer mois
    ) {
        PhoneUserMonthlyCaHmDTO stats = phoneUserStatsService.getMonthlyCaHmForPhoneUser(phoneUserId, annee, mois);
        return ResponseEntity.ok(stats);
    }
}
