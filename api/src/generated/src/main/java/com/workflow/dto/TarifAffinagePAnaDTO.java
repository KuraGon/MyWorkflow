package com.workflow.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.*;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Détail des tarifs d&#39;analyse &#39;P&#39; (par poids).
 */

@JsonTypeName("TarifAffinagePAna")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-07T10:14:28.616693700+01:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public class TarifAffinagePAnaDTO {

  private Long id;

  private String codeTarifPAna;

  private Long pacThirdId = null;

  private BigDecimal pdsMin;

  private BigDecimal pdsMax;

  private BigDecimal prixBrutKg;

  private BigDecimal prixBrutMin;

  private String commentaire = null;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime dateCreation;

  private String idCreation;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime dateModification = null;

  private String idModification = null;

  public TarifAffinagePAnaDTO id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  
  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public TarifAffinagePAnaDTO codeTarifPAna(String codeTarifPAna) {
    this.codeTarifPAna = codeTarifPAna;
    return this;
  }

  /**
   * Get codeTarifPAna
   * @return codeTarifPAna
   */
  
  @JsonProperty("codeTarifPAna")
  public String getCodeTarifPAna() {
    return codeTarifPAna;
  }

  public void setCodeTarifPAna(String codeTarifPAna) {
    this.codeTarifPAna = codeTarifPAna;
  }

  public TarifAffinagePAnaDTO pacThirdId(Long pacThirdId) {
    this.pacThirdId = pacThirdId;
    return this;
  }

  /**
   * Get pacThirdId
   * @return pacThirdId
   */
  
  @JsonProperty("pacThirdId")
  public Long getPacThirdId() {
    return pacThirdId;
  }

  public void setPacThirdId(Long pacThirdId) {
    this.pacThirdId = pacThirdId;
  }

  public TarifAffinagePAnaDTO pdsMin(BigDecimal pdsMin) {
    this.pdsMin = pdsMin;
    return this;
  }

  /**
   * Get pdsMin
   * @return pdsMin
   */
  @Valid 
  @JsonProperty("pdsMin")
  public BigDecimal getPdsMin() {
    return pdsMin;
  }

  public void setPdsMin(BigDecimal pdsMin) {
    this.pdsMin = pdsMin;
  }

  public TarifAffinagePAnaDTO pdsMax(BigDecimal pdsMax) {
    this.pdsMax = pdsMax;
    return this;
  }

  /**
   * Get pdsMax
   * @return pdsMax
   */
  @Valid 
  @JsonProperty("pdsMax")
  public BigDecimal getPdsMax() {
    return pdsMax;
  }

  public void setPdsMax(BigDecimal pdsMax) {
    this.pdsMax = pdsMax;
  }

  public TarifAffinagePAnaDTO prixBrutKg(BigDecimal prixBrutKg) {
    this.prixBrutKg = prixBrutKg;
    return this;
  }

  /**
   * Get prixBrutKg
   * @return prixBrutKg
   */
  @Valid 
  @JsonProperty("prixBrutKg")
  public BigDecimal getPrixBrutKg() {
    return prixBrutKg;
  }

  public void setPrixBrutKg(BigDecimal prixBrutKg) {
    this.prixBrutKg = prixBrutKg;
  }

  public TarifAffinagePAnaDTO prixBrutMin(BigDecimal prixBrutMin) {
    this.prixBrutMin = prixBrutMin;
    return this;
  }

  /**
   * Get prixBrutMin
   * @return prixBrutMin
   */
  @Valid 
  @JsonProperty("prixBrutMin")
  public BigDecimal getPrixBrutMin() {
    return prixBrutMin;
  }

  public void setPrixBrutMin(BigDecimal prixBrutMin) {
    this.prixBrutMin = prixBrutMin;
  }

  public TarifAffinagePAnaDTO commentaire(String commentaire) {
    this.commentaire = commentaire;
    return this;
  }

  /**
   * Get commentaire
   * @return commentaire
   */
  
  @JsonProperty("commentaire")
  public String getCommentaire() {
    return commentaire;
  }

  public void setCommentaire(String commentaire) {
    this.commentaire = commentaire;
  }

  public TarifAffinagePAnaDTO dateCreation(OffsetDateTime dateCreation) {
    this.dateCreation = dateCreation;
    return this;
  }

  /**
   * Get dateCreation
   * @return dateCreation
   */
  @Valid 
  @JsonProperty("dateCreation")
  public OffsetDateTime getDateCreation() {
    return dateCreation;
  }

  public void setDateCreation(OffsetDateTime dateCreation) {
    this.dateCreation = dateCreation;
  }

  public TarifAffinagePAnaDTO idCreation(String idCreation) {
    this.idCreation = idCreation;
    return this;
  }

  /**
   * Get idCreation
   * @return idCreation
   */
  
  @JsonProperty("idCreation")
  public String getIdCreation() {
    return idCreation;
  }

  public void setIdCreation(String idCreation) {
    this.idCreation = idCreation;
  }

  public TarifAffinagePAnaDTO dateModification(OffsetDateTime dateModification) {
    this.dateModification = dateModification;
    return this;
  }

  /**
   * Get dateModification
   * @return dateModification
   */
  @Valid 
  @JsonProperty("dateModification")
  public OffsetDateTime getDateModification() {
    return dateModification;
  }

  public void setDateModification(OffsetDateTime dateModification) {
    this.dateModification = dateModification;
  }

  public TarifAffinagePAnaDTO idModification(String idModification) {
    this.idModification = idModification;
    return this;
  }

  /**
   * Get idModification
   * @return idModification
   */
  
  @JsonProperty("idModification")
  public String getIdModification() {
    return idModification;
  }

  public void setIdModification(String idModification) {
    this.idModification = idModification;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TarifAffinagePAnaDTO tarifAffinagePAna = (TarifAffinagePAnaDTO) o;
    return Objects.equals(this.id, tarifAffinagePAna.id) &&
        Objects.equals(this.codeTarifPAna, tarifAffinagePAna.codeTarifPAna) &&
        Objects.equals(this.pacThirdId, tarifAffinagePAna.pacThirdId) &&
        Objects.equals(this.pdsMin, tarifAffinagePAna.pdsMin) &&
        Objects.equals(this.pdsMax, tarifAffinagePAna.pdsMax) &&
        Objects.equals(this.prixBrutKg, tarifAffinagePAna.prixBrutKg) &&
        Objects.equals(this.prixBrutMin, tarifAffinagePAna.prixBrutMin) &&
        Objects.equals(this.commentaire, tarifAffinagePAna.commentaire) &&
        Objects.equals(this.dateCreation, tarifAffinagePAna.dateCreation) &&
        Objects.equals(this.idCreation, tarifAffinagePAna.idCreation) &&
        Objects.equals(this.dateModification, tarifAffinagePAna.dateModification) &&
        Objects.equals(this.idModification, tarifAffinagePAna.idModification);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, codeTarifPAna, pacThirdId, pdsMin, pdsMax, prixBrutKg, prixBrutMin, commentaire, dateCreation, idCreation, dateModification, idModification);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TarifAffinagePAnaDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    codeTarifPAna: ").append(toIndentedString(codeTarifPAna)).append("\n");
    sb.append("    pacThirdId: ").append(toIndentedString(pacThirdId)).append("\n");
    sb.append("    pdsMin: ").append(toIndentedString(pdsMin)).append("\n");
    sb.append("    pdsMax: ").append(toIndentedString(pdsMax)).append("\n");
    sb.append("    prixBrutKg: ").append(toIndentedString(prixBrutKg)).append("\n");
    sb.append("    prixBrutMin: ").append(toIndentedString(prixBrutMin)).append("\n");
    sb.append("    commentaire: ").append(toIndentedString(commentaire)).append("\n");
    sb.append("    dateCreation: ").append(toIndentedString(dateCreation)).append("\n");
    sb.append("    idCreation: ").append(toIndentedString(idCreation)).append("\n");
    sb.append("    dateModification: ").append(toIndentedString(dateModification)).append("\n");
    sb.append("    idModification: ").append(toIndentedString(idModification)).append("\n");
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

