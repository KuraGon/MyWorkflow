package com.saamp.workflow.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "phone_log_imports",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_phone_log_imports_checksum", columnNames = "checksum")
        }
)
@Getter
@Setter
public class PhoneLogImportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(name = "file_name", length = 255, nullable = false)
    private String fileName;

    @Column(name = "checksum", length = 64, nullable = false)
    private String checksum;

    @Column(name = "size_bytes", nullable = false)
    private long sizeBytes;

    @Column(name = "imported_at", nullable = false)
    private LocalDateTime importedAt;

    @Column(name = "status", length = 20, nullable = false)
    private String status; // SUCCESS / FAILED / PARTIAL

    @Column(name = "message", length = 1000)
    private String message;
}
