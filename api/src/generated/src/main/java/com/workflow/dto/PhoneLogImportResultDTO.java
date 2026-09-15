package com.workflow.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.*;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Résultat de l&#39;import de fichiers CSV de logs téléphoniques
 */

@JsonTypeName("PhoneLogImportResult")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-07T10:14:28.616693700+01:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public class PhoneLogImportResultDTO {

  private Integer fileCount;

  private Long totalRows;

  private Long importedRows;

  @Valid
  private List<String> errors = new ArrayList<>();

  public PhoneLogImportResultDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public PhoneLogImportResultDTO(Integer fileCount, Long totalRows, Long importedRows, List<String> errors) {
    this.fileCount = fileCount;
    this.totalRows = totalRows;
    this.importedRows = importedRows;
    this.errors = errors;
  }

  public PhoneLogImportResultDTO fileCount(Integer fileCount) {
    this.fileCount = fileCount;
    return this;
  }

  /**
   * Nombre de fichiers reçus
   * @return fileCount
   */
  @NotNull 
  @JsonProperty("fileCount")
  public Integer getFileCount() {
    return fileCount;
  }

  public void setFileCount(Integer fileCount) {
    this.fileCount = fileCount;
  }

  public PhoneLogImportResultDTO totalRows(Long totalRows) {
    this.totalRows = totalRows;
    return this;
  }

  /**
   * Nombre total de lignes lues
   * @return totalRows
   */
  @NotNull 
  @JsonProperty("totalRows")
  public Long getTotalRows() {
    return totalRows;
  }

  public void setTotalRows(Long totalRows) {
    this.totalRows = totalRows;
  }

  public PhoneLogImportResultDTO importedRows(Long importedRows) {
    this.importedRows = importedRows;
    return this;
  }

  /**
   * Nombre de lignes importées
   * @return importedRows
   */
  @NotNull 
  @JsonProperty("importedRows")
  public Long getImportedRows() {
    return importedRows;
  }

  public void setImportedRows(Long importedRows) {
    this.importedRows = importedRows;
  }

  public PhoneLogImportResultDTO errors(List<String> errors) {
    this.errors = errors;
    return this;
  }

  public PhoneLogImportResultDTO addErrorsItem(String errorsItem) {
    if (this.errors == null) {
      this.errors = new ArrayList<>();
    }
    this.errors.add(errorsItem);
    return this;
  }

  /**
   * Liste des erreurs rencontrées
   * @return errors
   */
  @NotNull 
  @JsonProperty("errors")
  public List<String> getErrors() {
    return errors;
  }

  public void setErrors(List<String> errors) {
    this.errors = errors;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PhoneLogImportResultDTO phoneLogImportResult = (PhoneLogImportResultDTO) o;
    return Objects.equals(this.fileCount, phoneLogImportResult.fileCount) &&
        Objects.equals(this.totalRows, phoneLogImportResult.totalRows) &&
        Objects.equals(this.importedRows, phoneLogImportResult.importedRows) &&
        Objects.equals(this.errors, phoneLogImportResult.errors);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fileCount, totalRows, importedRows, errors);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PhoneLogImportResultDTO {\n");
    sb.append("    fileCount: ").append(toIndentedString(fileCount)).append("\n");
    sb.append("    totalRows: ").append(toIndentedString(totalRows)).append("\n");
    sb.append("    importedRows: ").append(toIndentedString(importedRows)).append("\n");
    sb.append("    errors: ").append(toIndentedString(errors)).append("\n");
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

