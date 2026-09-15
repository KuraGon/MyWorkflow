package com.saamp.workflow.service;

import com.saamp.proconcept.model.PcsPcUser;
import com.saamp.proconcept.repository.PcsPcUserRepository;
import com.saamp.workflow.entity.UserEntity;
import com.saamp.workflow.repository.UserRepository;
import com.workflow.dto.UserSyncResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserSyncService {

    private final PcsPcUserRepository oracleRepo;
    private final UserRepository postgresRepo;

    @Transactional
    public UserSyncResponseDTO syncUsersFromOracle() {
        log.info("--- Début Synchro Users ---");

        List<PcsPcUser> oracleUsers = oracleRepo.findAll();
        int processed = 0;
        int ignored = 0;

        for (PcsPcUser oracleUser : oracleUsers) {
            // Règle métier : Ignorer les groupes
            if (oracleUser.getPcCompId() == null) {
                ignored++;
                continue;
            }

            UserEntity user = postgresRepo.findByLegacyId(oracleUser.getId())
                    .orElse(new UserEntity());

            // ... (Toute ta logique de mapping existante reste ici) ...
            user.setLegacyId(oracleUser.getId());
            user.setLegacyGroupId(oracleUser.getPcCompId());
            user.setUsername(oracleUser.getUsername());
            user.setFirstName(oracleUser.getFirstname());
            user.setLastName(oracleUser.getLastname());
            user.setActive(oracleUser.getActive() != null && oracleUser.getActive() == 1);

            if (oracleUser.getEmail() != null && !oracleUser.getEmail().isBlank()) {
                user.setEmail(oracleUser.getEmail());
            } else {
                user.setEmail(oracleUser.getUsername().toLowerCase() + "@saamp.migration");
            }

            if (user.getId() == null) {
                user.setPassword(oracleUser.getPassword());
           }

            postgresRepo.save(user);
            processed++;
        }

        log.info("--- Fin Synchro : {} traités, {} ignorés ---", processed, ignored);

        // Construction de la réponse
        UserSyncResponseDTO response = new UserSyncResponseDTO();
        response.setSuccess(true);
        response.setMessage("Synchronisation terminée.");
        response.setUsersProcessed(processed);
        response.setGroupsIgnored(ignored);

        return response;
    }
}