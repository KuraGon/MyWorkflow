package com.workflow.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.*;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Entrée d&#39;historique d&#39;import de fichier
 */

@JsonTypeName("PhoneLogImportHistory")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-07T10:14:28.616693700+01:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public class PhoneLogImportHistoryDTO {

  private UUID id;

  private String fileName;

  private String checksum;

  private Long sizeBytes;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime importedAt;

  private String status;

  private String message = null;

  public PhoneLogImportHistoryDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public PhoneLogImportHistoryDTO(UUID id, String fileName, String checksum, Long sizeBytes, OffsetDateTime importedAt, String status) {
    this.id = id;
    this.fileName = fileName;
    this.checksum = checksum;
    this.sizeBytes = sizeBytes;
    this.importedAt = importedAt;
    this.status = status;
  }

  public PhoneLogImportHistoryDTO id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  @NotNull @Valid 
  @JsonProperty("id")
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public PhoneLogImportHistoryDTO fileName(String fileName) {
    this.fileName = fileName;
    return this;
  }

  /**
   * Get fileName
   * @return fileName
   */
  @NotNull 
  @JsonProperty("fileName")
  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public PhoneLogImportHistoryDTO checksum(String checksum) {
    this.checksum = checksum;
    return this;
  }

  /**
   * Empreinte SHA-256
   * @return checksum
   */
  @NotNull 
  @JsonProperty("checksum")
  public String getChecksum() {
    return checksum;
  }

  public void setChecksum(String checksum) {
    this.checksum = checksum;
  }

  public PhoneLogImportHistoryDTO sizeBytes(Long sizeBytes) {
    this.sizeBytes = sizeBytes;
    return this;
  }

  /**
   * Get sizeBytes
   * @return sizeBytes
   */
  @NotNull 
  @JsonProperty("sizeBytes")
  public Long getSizeBytes() {
    return sizeBytes;
  }

  public void setSizeBytes(Long sizeBytes) {
    this.sizeBytes = sizeBytes;
  }

  public PhoneLogImportHistoryDTO importedAt(OffsetDateTime importedAt) {
    this.importedAt = importedAt;
    return this;
  }

  /**
   * Get importedAt
   * @return importedAt
   */
  @NotNull @Valid 
  @JsonProperty("importedAt")
  public OffsetDateTime getImportedAt() {
    return importedAt;
  }

  public void setImportedAt(OffsetDateTime importedAt) {
    this.importedAt = importedAt;
  }

  public PhoneLogImportHistoryDTO status(String status) {
    this.status = status;
    return this;
  }

  /**
   * IN_PROGRESS
   * @return status
   */
  @NotNull 
  @JsonProperty("status")
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public PhoneLogImportHistoryDTO message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
   */
  
  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PhoneLogImportHistoryDTO phoneLogImportHistory = (PhoneLogImportHistoryDTO) o;
    return Objects.equals(this.id, phoneLogImportHistory.id) &&
        Objects.equals(this.fileName, phoneLogImportHistory.fileName) &&
        Objects.equals(this.checksum, phoneLogImportHistory.checksum) &&
        Objects.equals(this.sizeBytes, phoneLogImportHistory.sizeBytes) &&
        Objects.equals(this.importedAt, phoneLogImportHistory.importedAt) &&
        Objects.equals(this.status, phoneLogImportHistory.status) &&
        Objects.equals(this.message, phoneLogImportHistory.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, fileName, checksum, sizeBytes, importedAt, status, message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PhoneLogImportHistoryDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    fileName: ").append(toIndentedString(fileName)).append("\n");
    sb.append("    checksum: ").append(toIndentedString(checksum)).append("\n");
    sb.append("    sizeBytes: ").append(toIndentedString(sizeBytes)).append("\n");
    sb.append("    importedAt: ").append(toIndentedString(importedAt)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

