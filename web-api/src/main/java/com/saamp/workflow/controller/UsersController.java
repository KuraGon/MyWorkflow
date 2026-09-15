package com.saamp.workflow.controller;

import com.saamp.workflow.service.UserSyncService;
import com.workflow.api.UsersApi;
import com.workflow.dto.UserDTO;
import com.workflow.dto.UserSyncResponseDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "basicAuth")
public class UsersController implements UsersApi {
    private final UserSyncService userSyncService;

    @Override
    public ResponseEntity<UserDTO> getUserById(
            @PathVariable("id") UUID id
    ) {
        return null;
    }
/*
    @Override
    public ResponseEntity<UserSyncResponseDTO> syncUsersFromLegacy() {
        try {
            UserSyncResponseDTO result = userSyncService.syncUsersFromOracle();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            // En cas d'erreur technique (ex: base Oracle inaccessible)
            UserSyncResponseDTO errorResponse = new UserSyncResponseDTO();
            errorResponse.setSuccess(false);
            errorResponse.setMessage("Erreur lors de la synchro : " + e.getMessage());
            errorResponse.setUsersProcessed(0);
            errorResponse.setGroupsIgnored(0);
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }
 */
}
