package com.saamp.workflow.repository;

import com.saamp.workflow.entity.PhoneLogImportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PhoneLogImportRepository extends JpaRepository<PhoneLogImportEntity, UUID> {

    Optional<PhoneLogImportEntity> findByChecksum(String checksum);
}
