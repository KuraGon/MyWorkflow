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
 * Tarif &#39;F&#39; (par % métaux).
 */

@JsonTypeName("TarifAffinageF")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-07T10:14:28.616693700+01:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public class TarifAffinageFDTO {

  private Long id;

  private String dicGcoAttributeFree06Id;

  private String codeTarif;

  private BigDecimal pctAuMin;

  private BigDecimal pctAuMax;

  private BigDecimal pctAgMin;

  private BigDecimal pctAgMax;

  private BigDecimal pctPtMin;

  private BigDecimal pctPtMax;

  private BigDecimal pctPdMin;

  private BigDecimal pctPdMax;

  private BigDecimal pctRhMin;

  private BigDecimal pctRhMax;

  private BigDecimal pctIrMin;

  private BigDecimal pctIrMax;

  private String commentaire = null;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime dateCreation;

  private String idCreation;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime dateModification = null;

  private String idModification = null;

  public TarifAffinageFDTO id(Long id) {
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

  public TarifAffinageFDTO dicGcoAttributeFree06Id(String dicGcoAttributeFree06Id) {
    this.dicGcoAttributeFree06Id = dicGcoAttributeFree06Id;
    return this;
  }

  /**
   * Get dicGcoAttributeFree06Id
   * @return dicGcoAttributeFree06Id
   */
  
  @JsonProperty("dicGcoAttributeFree06Id")
  public String getDicGcoAttributeFree06Id() {
    return dicGcoAttributeFree06Id;
  }

  public void setDicGcoAttributeFree06Id(String dicGcoAttributeFree06Id) {
    this.dicGcoAttributeFree06Id = dicGcoAttributeFree06Id;
  }

  public TarifAffinageFDTO codeTarif(String codeTarif) {
    this.codeTarif = codeTarif;
    return this;
  }

  /**
   * Get codeTarif
   * @return codeTarif
   */
  
  @JsonProperty("codeTarif")
  public String getCodeTarif() {
    return codeTarif;
  }

  public void setCodeTarif(String codeTarif) {
    this.codeTarif = codeTarif;
  }

  public TarifAffinageFDTO pctAuMin(BigDecimal pctAuMin) {
    this.pctAuMin = pctAuMin;
    return this;
  }

  /**
   * Get pctAuMin
   * @return pctAuMin
   */
  @Valid 
  @JsonProperty("pctAuMin")
  public BigDecimal getPctAuMin() {
    return pctAuMin;
  }

  public void setPctAuMin(BigDecimal pctAuMin) {
    this.pctAuMin = pctAuMin;
  }

  public TarifAffinageFDTO pctAuMax(BigDecimal pctAuMax) {
    this.pctAuMax = pctAuMax;
    return this;
  }

  /**
   * Get pctAuMax
   * @return pctAuMax
   */
  @Valid 
  @JsonProperty("pctAuMax")
  public BigDecimal getPctAuMax() {
    return pctAuMax;
  }

  public void setPctAuMax(BigDecimal pctAuMax) {
    this.pctAuMax = pctAuMax;
  }

  public TarifAffinageFDTO pctAgMin(BigDecimal pctAgMin) {
    this.pctAgMin = pctAgMin;
    return this;
  }

  /**
   * Get pctAgMin
   * @return pctAgMin
   */
  @Valid 
  @JsonProperty("pctAgMin")
  public BigDecimal getPctAgMin() {
    return pctAgMin;
  }

  public void setPctAgMin(BigDecimal pctAgMin) {
    this.pctAgMin = pctAgMin;
  }

  public TarifAffinageFDTO pctAgMax(BigDecimal pctAgMax) {
    this.pctAgMax = pctAgMax;
    return this;
  }

  /**
   * Get pctAgMax
   * @return pctAgMax
   */
  @Valid 
  @JsonProperty("pctAgMax")
  public BigDecimal getPctAgMax() {
    return pctAgMax;
  }

  public void setPctAgMax(BigDecimal pctAgMax) {
    this.pctAgMax = pctAgMax;
  }

  public TarifAffinageFDTO pctPtMin(BigDecimal pctPtMin) {
    this.pctPtMin = pctPtMin;
    return this;
  }

  /**
   * Get pctPtMin
   * @return pctPtMin
   */
  @Valid 
  @JsonProperty("pctPtMin")
  public BigDecimal getPctPtMin() {
    return pctPtMin;
  }

  public void setPctPtMin(BigDecimal pctPtMin) {
    this.pctPtMin = pctPtMin;
  }

  public TarifAffinageFDTO pctPtMax(BigDecimal pctPtMax) {
    this.pctPtMax = pctPtMax;
    return this;
  }

  /**
   * Get pctPtMax
   * @return pctPtMax
   */
  @Valid 
  @JsonProperty("pctPtMax")
  public BigDecimal getPctPtMax() {
    return pctPtMax;
  }

  public void setPctPtMax(BigDecimal pctPtMax) {
    this.pctPtMax = pctPtMax;
  }

  public TarifAffinageFDTO pctPdMin(BigDecimal pctPdMin) {
    this.pctPdMin = pctPdMin;
    return this;
  }

  /**
   * Get pctPdMin
   * @return pctPdMin
   */
  @Valid 
  @JsonProperty("pctPdMin")
  public BigDecimal getPctPdMin() {
    return pctPdMin;
  }

  public void setPctPdMin(BigDecimal pctPdMin) {
    this.pctPdMin = pctPdMin;
  }

  public TarifAffinageFDTO pctPdMax(BigDecimal pctPdMax) {
    this.pctPdMax = pctPdMax;
    return this;
  }

  /**
   * Get pctPdMax
   * @return pctPdMax
   */
  @Valid 
  @JsonProperty("pctPdMax")
  public BigDecimal getPctPdMax() {
    return pctPdMax;
  }

  public void setPctPdMax(BigDecimal pctPdMax) {
    this.pctPdMax = pctPdMax;
  }

  public TarifAffinageFDTO pctRhMin(BigDecimal pctRhMin) {
    this.pctRhMin = pctRhMin;
    return this;
  }

  /**
   * Get pctRhMin
   * @return pctRhMin
   */
  @Valid 
  @JsonProperty("pctRhMin")
  public BigDecimal getPctRhMin() {
    return pctRhMin;
  }

  public void setPctRhMin(BigDecimal pctRhMin) {
    this.pctRhMin = pctRhMin;
  }

  public TarifAffinageFDTO pctRhMax(BigDecimal pctRhMax) {
    this.pctRhMax = pctRhMax;
    return this;
  }

  /**
   * Get pctRhMax
   * @return pctRhMax
   */
  @Valid 
  @JsonProperty("pctRhMax")
  public BigDecimal getPctRhMax() {
    return pctRhMax;
  }

  public void setPctRhMax(BigDecimal pctRhMax) {
    this.pctRhMax = pctRhMax;
  }

  public TarifAffinageFDTO pctIrMin(BigDecimal pctIrMin) {
    this.pctIrMin = pctIrMin;
    return this;
  }

  /**
   * Get pctIrMin
   * @return pctIrMin
   */
  @Valid 
  @JsonProperty("pctIrMin")
  public BigDecimal getPctIrMin() {
    return pctIrMin;
  }

  public void setPctIrMin(BigDecimal pctIrMin) {
    this.pctIrMin = pctIrMin;
  }

  public TarifAffinageFDTO pctIrMax(BigDecimal pctIrMax) {
    this.pctIrMax = pctIrMax;
    return this;
  }

  /**
   * Get pctIrMax
   * @return pctIrMax
   */
  @Valid 
  @JsonProperty("pctIrMax")
  public BigDecimal getPctIrMax() {
    return pctIrMax;
  }

  public void setPctIrMax(BigDecimal pctIrMax) {
    this.pctIrMax = pctIrMax;
  }

  public TarifAffinageFDTO commentaire(String commentaire) {
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

  public TarifAffinageFDTO dateCreation(OffsetDateTime dateCreation) {
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

  public TarifAffinageFDTO idCreation(String idCreation) {
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

  public TarifAffinageFDTO dateModification(OffsetDateTime dateModification) {
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

  public TarifAffinageFDTO idModification(String idModification) {
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
    TarifAffinageFDTO tarifAffinageF = (TarifAffinageFDTO) o;
    return Objects.equals(this.id, tarifAffinageF.id) &&
        Objects.equals(this.dicGcoAttributeFree06Id, tarifAffinageF.dicGcoAttributeFree06Id) &&
        Objects.equals(this.codeTarif, tarifAffinageF.codeTarif) &&
        Objects.equals(this.pctAuMin, tarifAffinageF.pctAuMin) &&
        Objects.equals(this.pctAuMax, tarifAffinageF.pctAuMax) &&
        Objects.equals(this.pctAgMin, tarifAffinageF.pctAgMin) &&
        Objects.equals(this.pctAgMax, tarifAffinageF.pctAgMax) &&
        Objects.equals(this.pctPtMin, tarifAffinageF.pctPtMin) &&
        Objects.equals(this.pctPtMax, tarifAffinageF.pctPtMax) &&
        Objects.equals(this.pctPdMin, tarifAffinageF.pctPdMin) &&
        Objects.equals(this.pctPdMax, tarifAffinageF.pctPdMax) &&
        Objects.equals(this.pctRhMin, tarifAffinageF.pctRhMin) &&
        Objects.equals(this.pctRhMax, tarifAffinageF.pctRhMax) &&
        Objects.equals(this.pctIrMin, tarifAffinageF.pctIrMin) &&
        Objects.equals(this.pctIrMax, tarifAffinageF.pctIrMax) &&
        Objects.equals(this.commentaire, tarifAffinageF.commentaire) &&
        Objects.equals(this.dateCreation, tarifAffinageF.dateCreation) &&
        Objects.equals(this.idCreation, tarifAffinageF.idCreation) &&
        Objects.equals(this.dateModification, tarifAffinageF.dateModification) &&
        Objects.equals(this.idModification, tarifAffinageF.idModification);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, dicGcoAttributeFree06Id, codeTarif, pctAuMin, pctAuMax, pctAgMin, pctAgMax, pctPtMin, pctPtMax, pctPdMin, pctPdMax, pctRhMin, pctRhMax, pctIrMin, pctIrMax, commentaire, dateCreation, idCreation, dateModification, idModification);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TarifAffinageFDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    dicGcoAttributeFree06Id: ").append(toIndentedString(dicGcoAttributeFree06Id)).append("\n");
    sb.append("    codeTarif: ").append(toIndentedString(codeTarif)).append("\n");
    sb.append("    pctAuMin: ").append(toIndentedString(pctAuMin)).append("\n");
    sb.append("    pctAuMax: ").append(toIndentedString(pctAuMax)).append("\n");
    sb.append("    pctAgMin: ").append(toIndentedString(pctAgMin)).append("\n");
    sb.append("    pctAgMax: ").append(toIndentedString(pctAgMax)).append("\n");
    sb.append("    pctPtMin: ").append(toIndentedString(pctPtMin)).append("\n");
    sb.append("    pctPtMax: ").append(toIndentedString(pctPtMax)).append("\n");
    sb.append("    pctPdMin: ").append(toIndentedString(pctPdMin)).append("\n");
    sb.append("    pctPdMax: ").append(toIndentedString(pctPdMax)).append("\n");
    sb.append("    pctRhMin: ").append(toIndentedString(pctRhMin)).append("\n");
    sb.append("    pctRhMax: ").append(toIndentedString(pctRhMax)).append("\n");
    sb.append("    pctIrMin: ").append(toIndentedString(pctIrMin)).append("\n");
    sb.append("    pctIrMax: ").append(toIndentedString(pctIrMax)).append("\n");
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

