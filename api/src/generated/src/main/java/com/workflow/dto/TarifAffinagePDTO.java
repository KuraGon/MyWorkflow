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
 * Tarif principal (par % métaux) liant les codes de tarifs détaillés.
 */

@JsonTypeName("TarifAffinageP")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-07T10:14:28.616693700+01:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public class TarifAffinagePDTO {

  private Long id;

  private String dicGcoAttributeFree06Id;

  private String codeTarifPAff;

  private String codeTarifPAna;

  private String codeTarifPHom;

  private String codeTarifPPre;

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

  public TarifAffinagePDTO id(Long id) {
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

  public TarifAffinagePDTO dicGcoAttributeFree06Id(String dicGcoAttributeFree06Id) {
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

  public TarifAffinagePDTO codeTarifPAff(String codeTarifPAff) {
    this.codeTarifPAff = codeTarifPAff;
    return this;
  }

  /**
   * Get codeTarifPAff
   * @return codeTarifPAff
   */
  
  @JsonProperty("codeTarifPAff")
  public String getCodeTarifPAff() {
    return codeTarifPAff;
  }

  public void setCodeTarifPAff(String codeTarifPAff) {
    this.codeTarifPAff = codeTarifPAff;
  }

  public TarifAffinagePDTO codeTarifPAna(String codeTarifPAna) {
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

  public TarifAffinagePDTO codeTarifPHom(String codeTarifPHom) {
    this.codeTarifPHom = codeTarifPHom;
    return this;
  }

  /**
   * Get codeTarifPHom
   * @return codeTarifPHom
   */
  
  @JsonProperty("codeTarifPHom")
  public String getCodeTarifPHom() {
    return codeTarifPHom;
  }

  public void setCodeTarifPHom(String codeTarifPHom) {
    this.codeTarifPHom = codeTarifPHom;
  }

  public TarifAffinagePDTO codeTarifPPre(String codeTarifPPre) {
    this.codeTarifPPre = codeTarifPPre;
    return this;
  }

  /**
   * Get codeTarifPPre
   * @return codeTarifPPre
   */
  
  @JsonProperty("codeTarifPPre")
  public String getCodeTarifPPre() {
    return codeTarifPPre;
  }

  public void setCodeTarifPPre(String codeTarifPPre) {
    this.codeTarifPPre = codeTarifPPre;
  }

  public TarifAffinagePDTO pctAuMin(BigDecimal pctAuMin) {
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

  public TarifAffinagePDTO pctAuMax(BigDecimal pctAuMax) {
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

  public TarifAffinagePDTO pctAgMin(BigDecimal pctAgMin) {
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

  public TarifAffinagePDTO pctAgMax(BigDecimal pctAgMax) {
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

  public TarifAffinagePDTO pctPtMin(BigDecimal pctPtMin) {
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

  public TarifAffinagePDTO pctPtMax(BigDecimal pctPtMax) {
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

  public TarifAffinagePDTO pctPdMin(BigDecimal pctPdMin) {
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

  public TarifAffinagePDTO pctPdMax(BigDecimal pctPdMax) {
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

  public TarifAffinagePDTO pctRhMin(BigDecimal pctRhMin) {
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

  public TarifAffinagePDTO pctRhMax(BigDecimal pctRhMax) {
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

  public TarifAffinagePDTO pctIrMin(BigDecimal pctIrMin) {
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

  public TarifAffinagePDTO pctIrMax(BigDecimal pctIrMax) {
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

  public TarifAffinagePDTO commentaire(String commentaire) {
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

  public TarifAffinagePDTO dateCreation(OffsetDateTime dateCreation) {
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

  public TarifAffinagePDTO idCreation(String idCreation) {
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

  public TarifAffinagePDTO dateModification(OffsetDateTime dateModification) {
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

  public TarifAffinagePDTO idModification(String idModification) {
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
    TarifAffinagePDTO tarifAffinageP = (TarifAffinagePDTO) o;
    return Objects.equals(this.id, tarifAffinageP.id) &&
        Objects.equals(this.dicGcoAttributeFree06Id, tarifAffinageP.dicGcoAttributeFree06Id) &&
        Objects.equals(this.codeTarifPAff, tarifAffinageP.codeTarifPAff) &&
        Objects.equals(this.codeTarifPAna, tarifAffinageP.codeTarifPAna) &&
        Objects.equals(this.codeTarifPHom, tarifAffinageP.codeTarifPHom) &&
        Objects.equals(this.codeTarifPPre, tarifAffinageP.codeTarifPPre) &&
        Objects.equals(this.pctAuMin, tarifAffinageP.pctAuMin) &&
        Objects.equals(this.pctAuMax, tarifAffinageP.pctAuMax) &&
        Objects.equals(this.pctAgMin, tarifAffinageP.pctAgMin) &&
        Objects.equals(this.pctAgMax, tarifAffinageP.pctAgMax) &&
        Objects.equals(this.pctPtMin, tarifAffinageP.pctPtMin) &&
        Objects.equals(this.pctPtMax, tarifAffinageP.pctPtMax) &&
        Objects.equals(this.pctPdMin, tarifAffinageP.pctPdMin) &&
        Objects.equals(this.pctPdMax, tarifAffinageP.pctPdMax) &&
        Objects.equals(this.pctRhMin, tarifAffinageP.pctRhMin) &&
        Objects.equals(this.pctRhMax, tarifAffinageP.pctRhMax) &&
        Objects.equals(this.pctIrMin, tarifAffinageP.pctIrMin) &&
        Objects.equals(this.pctIrMax, tarifAffinageP.pctIrMax) &&
        Objects.equals(this.commentaire, tarifAffinageP.commentaire) &&
        Objects.equals(this.dateCreation, tarifAffinageP.dateCreation) &&
        Objects.equals(this.idCreation, tarifAffinageP.idCreation) &&
        Objects.equals(this.dateModification, tarifAffinageP.dateModification) &&
        Objects.equals(this.idModification, tarifAffinageP.idModification);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, dicGcoAttributeFree06Id, codeTarifPAff, codeTarifPAna, codeTarifPHom, codeTarifPPre, pctAuMin, pctAuMax, pctAgMin, pctAgMax, pctPtMin, pctPtMax, pctPdMin, pctPdMax, pctRhMin, pctRhMax, pctIrMin, pctIrMax, commentaire, dateCreation, idCreation, dateModification, idModification);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TarifAffinagePDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    dicGcoAttributeFree06Id: ").append(toIndentedString(dicGcoAttributeFree06Id)).append("\n");
    sb.append("    codeTarifPAff: ").append(toIndentedString(codeTarifPAff)).append("\n");
    sb.append("    codeTarifPAna: ").append(toIndentedString(codeTarifPAna)).append("\n");
    sb.append("    codeTarifPHom: ").append(toIndentedString(codeTarifPHom)).append("\n");
    sb.append("    codeTarifPPre: ").append(toIndentedString(codeTarifPPre)).append("\n");
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

