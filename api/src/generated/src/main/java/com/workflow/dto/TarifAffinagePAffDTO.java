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
 * Détail des tarifs d&#39;affinage &#39;P&#39; (par poids).
 */

@JsonTypeName("TarifAffinagePAff")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-07T10:14:28.616693700+01:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public class TarifAffinagePAffDTO {

  private Long id;

  private String codeTarifPAff;

  private Long pacThirdId = null;

  private BigDecimal pdsMin;

  private BigDecimal pdsMax;

  private BigDecimal prixBrutKg;

  private BigDecimal prixBrutMin;

  private BigDecimal pctAuPerteMin;

  private BigDecimal pdsAuPerteMinKg;

  private BigDecimal pdsAuPerteMinLot;

  private BigDecimal pctAgPerteMin;

  private BigDecimal pdsAgPerteMinKg;

  private BigDecimal pdsAgPerteMinLot;

  private BigDecimal pctPtPerteMin;

  private BigDecimal pdsPtPerteMinKg;

  private BigDecimal pdsPtPerteMinLot;

  private BigDecimal pctPdPerteMin;

  private BigDecimal pdsPdPerteMinKg;

  private BigDecimal pdsPdPerteMinLot;

  private BigDecimal pctRhPerteMin;

  private BigDecimal pdsRhPerteMinKg;

  private BigDecimal pdsRhPerteMinLot;

  private BigDecimal pctIrPerteMin;

  private BigDecimal pdsIrPerteMinKg;

  private BigDecimal pdsIrPerteMinLot;

  private String commentaire = null;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime dateCreation;

  private String idCreation;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime dateModification = null;

  private String idModification = null;

  public TarifAffinagePAffDTO id(Long id) {
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

  public TarifAffinagePAffDTO codeTarifPAff(String codeTarifPAff) {
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

  public TarifAffinagePAffDTO pacThirdId(Long pacThirdId) {
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

  public TarifAffinagePAffDTO pdsMin(BigDecimal pdsMin) {
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

  public TarifAffinagePAffDTO pdsMax(BigDecimal pdsMax) {
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

  public TarifAffinagePAffDTO prixBrutKg(BigDecimal prixBrutKg) {
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

  public TarifAffinagePAffDTO prixBrutMin(BigDecimal prixBrutMin) {
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

  public TarifAffinagePAffDTO pctAuPerteMin(BigDecimal pctAuPerteMin) {
    this.pctAuPerteMin = pctAuPerteMin;
    return this;
  }

  /**
   * Get pctAuPerteMin
   * @return pctAuPerteMin
   */
  @Valid 
  @JsonProperty("pctAuPerteMin")
  public BigDecimal getPctAuPerteMin() {
    return pctAuPerteMin;
  }

  public void setPctAuPerteMin(BigDecimal pctAuPerteMin) {
    this.pctAuPerteMin = pctAuPerteMin;
  }

  public TarifAffinagePAffDTO pdsAuPerteMinKg(BigDecimal pdsAuPerteMinKg) {
    this.pdsAuPerteMinKg = pdsAuPerteMinKg;
    return this;
  }

  /**
   * Get pdsAuPerteMinKg
   * @return pdsAuPerteMinKg
   */
  @Valid 
  @JsonProperty("pdsAuPerteMinKg")
  public BigDecimal getPdsAuPerteMinKg() {
    return pdsAuPerteMinKg;
  }

  public void setPdsAuPerteMinKg(BigDecimal pdsAuPerteMinKg) {
    this.pdsAuPerteMinKg = pdsAuPerteMinKg;
  }

  public TarifAffinagePAffDTO pdsAuPerteMinLot(BigDecimal pdsAuPerteMinLot) {
    this.pdsAuPerteMinLot = pdsAuPerteMinLot;
    return this;
  }

  /**
   * Get pdsAuPerteMinLot
   * @return pdsAuPerteMinLot
   */
  @Valid 
  @JsonProperty("pdsAuPerteMinLot")
  public BigDecimal getPdsAuPerteMinLot() {
    return pdsAuPerteMinLot;
  }

  public void setPdsAuPerteMinLot(BigDecimal pdsAuPerteMinLot) {
    this.pdsAuPerteMinLot = pdsAuPerteMinLot;
  }

  public TarifAffinagePAffDTO pctAgPerteMin(BigDecimal pctAgPerteMin) {
    this.pctAgPerteMin = pctAgPerteMin;
    return this;
  }

  /**
   * Get pctAgPerteMin
   * @return pctAgPerteMin
   */
  @Valid 
  @JsonProperty("pctAgPerteMin")
  public BigDecimal getPctAgPerteMin() {
    return pctAgPerteMin;
  }

  public void setPctAgPerteMin(BigDecimal pctAgPerteMin) {
    this.pctAgPerteMin = pctAgPerteMin;
  }

  public TarifAffinagePAffDTO pdsAgPerteMinKg(BigDecimal pdsAgPerteMinKg) {
    this.pdsAgPerteMinKg = pdsAgPerteMinKg;
    return this;
  }

  /**
   * Get pdsAgPerteMinKg
   * @return pdsAgPerteMinKg
   */
  @Valid 
  @JsonProperty("pdsAgPerteMinKg")
  public BigDecimal getPdsAgPerteMinKg() {
    return pdsAgPerteMinKg;
  }

  public void setPdsAgPerteMinKg(BigDecimal pdsAgPerteMinKg) {
    this.pdsAgPerteMinKg = pdsAgPerteMinKg;
  }

  public TarifAffinagePAffDTO pdsAgPerteMinLot(BigDecimal pdsAgPerteMinLot) {
    this.pdsAgPerteMinLot = pdsAgPerteMinLot;
    return this;
  }

  /**
   * Get pdsAgPerteMinLot
   * @return pdsAgPerteMinLot
   */
  @Valid 
  @JsonProperty("pdsAgPerteMinLot")
  public BigDecimal getPdsAgPerteMinLot() {
    return pdsAgPerteMinLot;
  }

  public void setPdsAgPerteMinLot(BigDecimal pdsAgPerteMinLot) {
    this.pdsAgPerteMinLot = pdsAgPerteMinLot;
  }

  public TarifAffinagePAffDTO pctPtPerteMin(BigDecimal pctPtPerteMin) {
    this.pctPtPerteMin = pctPtPerteMin;
    return this;
  }

  /**
   * Get pctPtPerteMin
   * @return pctPtPerteMin
   */
  @Valid 
  @JsonProperty("pctPtPerteMin")
  public BigDecimal getPctPtPerteMin() {
    return pctPtPerteMin;
  }

  public void setPctPtPerteMin(BigDecimal pctPtPerteMin) {
    this.pctPtPerteMin = pctPtPerteMin;
  }

  public TarifAffinagePAffDTO pdsPtPerteMinKg(BigDecimal pdsPtPerteMinKg) {
    this.pdsPtPerteMinKg = pdsPtPerteMinKg;
    return this;
  }

  /**
   * Get pdsPtPerteMinKg
   * @return pdsPtPerteMinKg
   */
  @Valid 
  @JsonProperty("pdsPtPerteMinKg")
  public BigDecimal getPdsPtPerteMinKg() {
    return pdsPtPerteMinKg;
  }

  public void setPdsPtPerteMinKg(BigDecimal pdsPtPerteMinKg) {
    this.pdsPtPerteMinKg = pdsPtPerteMinKg;
  }

  public TarifAffinagePAffDTO pdsPtPerteMinLot(BigDecimal pdsPtPerteMinLot) {
    this.pdsPtPerteMinLot = pdsPtPerteMinLot;
    return this;
  }

  /**
   * Get pdsPtPerteMinLot
   * @return pdsPtPerteMinLot
   */
  @Valid 
  @JsonProperty("pdsPtPerteMinLot")
  public BigDecimal getPdsPtPerteMinLot() {
    return pdsPtPerteMinLot;
  }

  public void setPdsPtPerteMinLot(BigDecimal pdsPtPerteMinLot) {
    this.pdsPtPerteMinLot = pdsPtPerteMinLot;
  }

  public TarifAffinagePAffDTO pctPdPerteMin(BigDecimal pctPdPerteMin) {
    this.pctPdPerteMin = pctPdPerteMin;
    return this;
  }

  /**
   * Get pctPdPerteMin
   * @return pctPdPerteMin
   */
  @Valid 
  @JsonProperty("pctPdPerteMin")
  public BigDecimal getPctPdPerteMin() {
    return pctPdPerteMin;
  }

  public void setPctPdPerteMin(BigDecimal pctPdPerteMin) {
    this.pctPdPerteMin = pctPdPerteMin;
  }

  public TarifAffinagePAffDTO pdsPdPerteMinKg(BigDecimal pdsPdPerteMinKg) {
    this.pdsPdPerteMinKg = pdsPdPerteMinKg;
    return this;
  }

  /**
   * Get pdsPdPerteMinKg
   * @return pdsPdPerteMinKg
   */
  @Valid 
  @JsonProperty("pdsPdPerteMinKg")
  public BigDecimal getPdsPdPerteMinKg() {
    return pdsPdPerteMinKg;
  }

  public void setPdsPdPerteMinKg(BigDecimal pdsPdPerteMinKg) {
    this.pdsPdPerteMinKg = pdsPdPerteMinKg;
  }

  public TarifAffinagePAffDTO pdsPdPerteMinLot(BigDecimal pdsPdPerteMinLot) {
    this.pdsPdPerteMinLot = pdsPdPerteMinLot;
    return this;
  }

  /**
   * Get pdsPdPerteMinLot
   * @return pdsPdPerteMinLot
   */
  @Valid 
  @JsonProperty("pdsPdPerteMinLot")
  public BigDecimal getPdsPdPerteMinLot() {
    return pdsPdPerteMinLot;
  }

  public void setPdsPdPerteMinLot(BigDecimal pdsPdPerteMinLot) {
    this.pdsPdPerteMinLot = pdsPdPerteMinLot;
  }

  public TarifAffinagePAffDTO pctRhPerteMin(BigDecimal pctRhPerteMin) {
    this.pctRhPerteMin = pctRhPerteMin;
    return this;
  }

  /**
   * Get pctRhPerteMin
   * @return pctRhPerteMin
   */
  @Valid 
  @JsonProperty("pctRhPerteMin")
  public BigDecimal getPctRhPerteMin() {
    return pctRhPerteMin;
  }

  public void setPctRhPerteMin(BigDecimal pctRhPerteMin) {
    this.pctRhPerteMin = pctRhPerteMin;
  }

  public TarifAffinagePAffDTO pdsRhPerteMinKg(BigDecimal pdsRhPerteMinKg) {
    this.pdsRhPerteMinKg = pdsRhPerteMinKg;
    return this;
  }

  /**
   * Get pdsRhPerteMinKg
   * @return pdsRhPerteMinKg
   */
  @Valid 
  @JsonProperty("pdsRhPerteMinKg")
  public BigDecimal getPdsRhPerteMinKg() {
    return pdsRhPerteMinKg;
  }

  public void setPdsRhPerteMinKg(BigDecimal pdsRhPerteMinKg) {
    this.pdsRhPerteMinKg = pdsRhPerteMinKg;
  }

  public TarifAffinagePAffDTO pdsRhPerteMinLot(BigDecimal pdsRhPerteMinLot) {
    this.pdsRhPerteMinLot = pdsRhPerteMinLot;
    return this;
  }

  /**
   * Get pdsRhPerteMinLot
   * @return pdsRhPerteMinLot
   */
  @Valid 
  @JsonProperty("pdsRhPerteMinLot")
  public BigDecimal getPdsRhPerteMinLot() {
    return pdsRhPerteMinLot;
  }

  public void setPdsRhPerteMinLot(BigDecimal pdsRhPerteMinLot) {
    this.pdsRhPerteMinLot = pdsRhPerteMinLot;
  }

  public TarifAffinagePAffDTO pctIrPerteMin(BigDecimal pctIrPerteMin) {
    this.pctIrPerteMin = pctIrPerteMin;
    return this;
  }

  /**
   * Get pctIrPerteMin
   * @return pctIrPerteMin
   */
  @Valid 
  @JsonProperty("pctIrPerteMin")
  public BigDecimal getPctIrPerteMin() {
    return pctIrPerteMin;
  }

  public void setPctIrPerteMin(BigDecimal pctIrPerteMin) {
    this.pctIrPerteMin = pctIrPerteMin;
  }

  public TarifAffinagePAffDTO pdsIrPerteMinKg(BigDecimal pdsIrPerteMinKg) {
    this.pdsIrPerteMinKg = pdsIrPerteMinKg;
    return this;
  }

  /**
   * Get pdsIrPerteMinKg
   * @return pdsIrPerteMinKg
   */
  @Valid 
  @JsonProperty("pdsIrPerteMinKg")
  public BigDecimal getPdsIrPerteMinKg() {
    return pdsIrPerteMinKg;
  }

  public void setPdsIrPerteMinKg(BigDecimal pdsIrPerteMinKg) {
    this.pdsIrPerteMinKg = pdsIrPerteMinKg;
  }

  public TarifAffinagePAffDTO pdsIrPerteMinLot(BigDecimal pdsIrPerteMinLot) {
    this.pdsIrPerteMinLot = pdsIrPerteMinLot;
    return this;
  }

  /**
   * Get pdsIrPerteMinLot
   * @return pdsIrPerteMinLot
   */
  @Valid 
  @JsonProperty("pdsIrPerteMinLot")
  public BigDecimal getPdsIrPerteMinLot() {
    return pdsIrPerteMinLot;
  }

  public void setPdsIrPerteMinLot(BigDecimal pdsIrPerteMinLot) {
    this.pdsIrPerteMinLot = pdsIrPerteMinLot;
  }

  public TarifAffinagePAffDTO commentaire(String commentaire) {
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

  public TarifAffinagePAffDTO dateCreation(OffsetDateTime dateCreation) {
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

  public TarifAffinagePAffDTO idCreation(String idCreation) {
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

  public TarifAffinagePAffDTO dateModification(OffsetDateTime dateModification) {
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

  public TarifAffinagePAffDTO idModification(String idModification) {
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
    TarifAffinagePAffDTO tarifAffinagePAff = (TarifAffinagePAffDTO) o;
    return Objects.equals(this.id, tarifAffinagePAff.id) &&
        Objects.equals(this.codeTarifPAff, tarifAffinagePAff.codeTarifPAff) &&
        Objects.equals(this.pacThirdId, tarifAffinagePAff.pacThirdId) &&
        Objects.equals(this.pdsMin, tarifAffinagePAff.pdsMin) &&
        Objects.equals(this.pdsMax, tarifAffinagePAff.pdsMax) &&
        Objects.equals(this.prixBrutKg, tarifAffinagePAff.prixBrutKg) &&
        Objects.equals(this.prixBrutMin, tarifAffinagePAff.prixBrutMin) &&
        Objects.equals(this.pctAuPerteMin, tarifAffinagePAff.pctAuPerteMin) &&
        Objects.equals(this.pdsAuPerteMinKg, tarifAffinagePAff.pdsAuPerteMinKg) &&
        Objects.equals(this.pdsAuPerteMinLot, tarifAffinagePAff.pdsAuPerteMinLot) &&
        Objects.equals(this.pctAgPerteMin, tarifAffinagePAff.pctAgPerteMin) &&
        Objects.equals(this.pdsAgPerteMinKg, tarifAffinagePAff.pdsAgPerteMinKg) &&
        Objects.equals(this.pdsAgPerteMinLot, tarifAffinagePAff.pdsAgPerteMinLot) &&
        Objects.equals(this.pctPtPerteMin, tarifAffinagePAff.pctPtPerteMin) &&
        Objects.equals(this.pdsPtPerteMinKg, tarifAffinagePAff.pdsPtPerteMinKg) &&
        Objects.equals(this.pdsPtPerteMinLot, tarifAffinagePAff.pdsPtPerteMinLot) &&
        Objects.equals(this.pctPdPerteMin, tarifAffinagePAff.pctPdPerteMin) &&
        Objects.equals(this.pdsPdPerteMinKg, tarifAffinagePAff.pdsPdPerteMinKg) &&
        Objects.equals(this.pdsPdPerteMinLot, tarifAffinagePAff.pdsPdPerteMinLot) &&
        Objects.equals(this.pctRhPerteMin, tarifAffinagePAff.pctRhPerteMin) &&
        Objects.equals(this.pdsRhPerteMinKg, tarifAffinagePAff.pdsRhPerteMinKg) &&
        Objects.equals(this.pdsRhPerteMinLot, tarifAffinagePAff.pdsRhPerteMinLot) &&
        Objects.equals(this.pctIrPerteMin, tarifAffinagePAff.pctIrPerteMin) &&
        Objects.equals(this.pdsIrPerteMinKg, tarifAffinagePAff.pdsIrPerteMinKg) &&
        Objects.equals(this.pdsIrPerteMinLot, tarifAffinagePAff.pdsIrPerteMinLot) &&
        Objects.equals(this.commentaire, tarifAffinagePAff.commentaire) &&
        Objects.equals(this.dateCreation, tarifAffinagePAff.dateCreation) &&
        Objects.equals(this.idCreation, tarifAffinagePAff.idCreation) &&
        Objects.equals(this.dateModification, tarifAffinagePAff.dateModification) &&
        Objects.equals(this.idModification, tarifAffinagePAff.idModification);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, codeTarifPAff, pacThirdId, pdsMin, pdsMax, prixBrutKg, prixBrutMin, pctAuPerteMin, pdsAuPerteMinKg, pdsAuPerteMinLot, pctAgPerteMin, pdsAgPerteMinKg, pdsAgPerteMinLot, pctPtPerteMin, pdsPtPerteMinKg, pdsPtPerteMinLot, pctPdPerteMin, pdsPdPerteMinKg, pdsPdPerteMinLot, pctRhPerteMin, pdsRhPerteMinKg, pdsRhPerteMinLot, pctIrPerteMin, pdsIrPerteMinKg, pdsIrPerteMinLot, commentaire, dateCreation, idCreation, dateModification, idModification);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TarifAffinagePAffDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    codeTarifPAff: ").append(toIndentedString(codeTarifPAff)).append("\n");
    sb.append("    pacThirdId: ").append(toIndentedString(pacThirdId)).append("\n");
    sb.append("    pdsMin: ").append(toIndentedString(pdsMin)).append("\n");
    sb.append("    pdsMax: ").append(toIndentedString(pdsMax)).append("\n");
    sb.append("    prixBrutKg: ").append(toIndentedString(prixBrutKg)).append("\n");
    sb.append("    prixBrutMin: ").append(toIndentedString(prixBrutMin)).append("\n");
    sb.append("    pctAuPerteMin: ").append(toIndentedString(pctAuPerteMin)).append("\n");
    sb.append("    pdsAuPerteMinKg: ").append(toIndentedString(pdsAuPerteMinKg)).append("\n");
    sb.append("    pdsAuPerteMinLot: ").append(toIndentedString(pdsAuPerteMinLot)).append("\n");
    sb.append("    pctAgPerteMin: ").append(toIndentedString(pctAgPerteMin)).append("\n");
    sb.append("    pdsAgPerteMinKg: ").append(toIndentedString(pdsAgPerteMinKg)).append("\n");
    sb.append("    pdsAgPerteMinLot: ").append(toIndentedString(pdsAgPerteMinLot)).append("\n");
    sb.append("    pctPtPerteMin: ").append(toIndentedString(pctPtPerteMin)).append("\n");
    sb.append("    pdsPtPerteMinKg: ").append(toIndentedString(pdsPtPerteMinKg)).append("\n");
    sb.append("    pdsPtPerteMinLot: ").append(toIndentedString(pdsPtPerteMinLot)).append("\n");
    sb.append("    pctPdPerteMin: ").append(toIndentedString(pctPdPerteMin)).append("\n");
    sb.append("    pdsPdPerteMinKg: ").append(toIndentedString(pdsPdPerteMinKg)).append("\n");
    sb.append("    pdsPdPerteMinLot: ").append(toIndentedString(pdsPdPerteMinLot)).append("\n");
    sb.append("    pctRhPerteMin: ").append(toIndentedString(pctRhPerteMin)).append("\n");
    sb.append("    pdsRhPerteMinKg: ").append(toIndentedString(pdsRhPerteMinKg)).append("\n");
    sb.append("    pdsRhPerteMinLot: ").append(toIndentedString(pdsRhPerteMinLot)).append("\n");
    sb.append("    pctIrPerteMin: ").append(toIndentedString(pctIrPerteMin)).append("\n");
    sb.append("    pdsIrPerteMinKg: ").append(toIndentedString(pdsIrPerteMinKg)).append("\n");
    sb.append("    pdsIrPerteMinLot: ").append(toIndentedString(pdsIrPerteMinLot)).append("\n");
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

