package com.saamp.workflow.controller;

import com.saamp.workflow.service.PhoneContactService;
import com.workflow.api.PhoneContactsApi;
import com.workflow.dto.PhoneContactDTO;
import com.workflow.dto.PhoneContactPageDTO;
import com.workflow.dto.PhoneContactUpdateRequestDTO;
import com.workflow.dto.PhoneUserDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Implémentation des endpoints OpenAPI PhoneContacts.
 *
 * Remarque :
 * - On sépare volontairement de PhoneLogsApiController pour ne pas impacter les endpoints existants.
 */
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "basicAuth")
public class PhoneContactsApiController implements PhoneContactsApi {

    private final PhoneContactService phoneContactService;

    @Override
    public ResponseEntity<PhoneContactPageDTO> getPhoneContactsPage(
            @NotNull @Min(0)  @Valid @RequestParam(value = "page", required = true, defaultValue = "0") Integer page,
            @NotNull @Min(1) @Max(200)  @Valid @RequestParam(value = "size", required = true, defaultValue = "25") Integer size,
            @Valid @RequestParam(value = "q", required = false) String q
    ) {
        PhoneContactPageDTO res = phoneContactService.getPage(q, page, size);
        return ResponseEntity.ok(res);
    }

    @Override
    public ResponseEntity<PhoneUserDTO> getPhoneContact(
            @PathVariable("id") UUID id
    ) {
        PhoneUserDTO res = phoneContactService.getPhoneUserByPhoneContact(id);
        return ResponseEntity.ok(res);
    }

    @Override
    public ResponseEntity<PhoneContactDTO> updatePhoneContact(UUID id, PhoneContactUpdateRequestDTO phoneContactUpdateRequestDTO) {
        PhoneContactDTO updated = phoneContactService.update(id, phoneContactUpdateRequestDTO);
        return ResponseEntity.ok(updated);
    }
}
