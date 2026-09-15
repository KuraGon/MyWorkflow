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
 * Détail des tarifs &#39;F&#39; (par poids).
 */

@JsonTypeName("TarifAffinageFDet")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-07T10:14:28.616693700+01:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public class TarifAffinageFDetDTO {

  private Long id;

  private String codeTarifDet;

  private String codeTarif;

  private Long pacThirdId = null;

  private BigDecimal pdsMin;

  private BigDecimal pdsMax;

  private BigDecimal prixBrutKg;

  private BigDecimal prixBrutMin;

  private BigDecimal pctAuPerteMin;

  private BigDecimal pdsAuPerteMinKg;

  private BigDecimal pdsAuPerteMinLot;

  private BigDecimal pctAuDecote;

  private BigDecimal pctAgPerteMin;

  private BigDecimal pdsAgPerteMinKg;

  private BigDecimal pdsAgPerteMinLot;

  private BigDecimal pctAgDecote;

  private BigDecimal pctPtPerteMin;

  private BigDecimal pdsPtPerteMinKg;

  private BigDecimal pdsPtPerteMinLot;

  private BigDecimal pctPtDecote;

  private BigDecimal pctPdPerteMin;

  private BigDecimal pdsPdPerteMinKg;

  private BigDecimal pdsPdPerteMinLot;

  private BigDecimal pctPdDecote;

  private BigDecimal pctRhPerteMin;

  private BigDecimal pdsRhPerteMinKg;

  private BigDecimal pdsRhPerteMinLot;

  private BigDecimal pctRhDecote;

  private BigDecimal pctIrPerteMin;

  private BigDecimal pdsIrPerteMinKg;

  private BigDecimal pdsIrPerteMinLot;

  private BigDecimal pctIrDecote;

  private String commentaire = null;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime dateCreation;

  private String idCreation;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime dateModification = null;

  private String idModification = null;

  public TarifAffinageFDetDTO id(Long id) {
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

  public TarifAffinageFDetDTO codeTarifDet(String codeTarifDet) {
    this.codeTarifDet = codeTarifDet;
    return this;
  }

  /**
   * Get codeTarifDet
   * @return codeTarifDet
   */
  
  @JsonProperty("codeTarifDet")
  public String getCodeTarifDet() {
    return codeTarifDet;
  }

  public void setCodeTarifDet(String codeTarifDet) {
    this.codeTarifDet = codeTarifDet;
  }

  public TarifAffinageFDetDTO codeTarif(String codeTarif) {
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

  public TarifAffinageFDetDTO pacThirdId(Long pacThirdId) {
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

  public TarifAffinageFDetDTO pdsMin(BigDecimal pdsMin) {
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

  public TarifAffinageFDetDTO pdsMax(BigDecimal pdsMax) {
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

  public TarifAffinageFDetDTO prixBrutKg(BigDecimal prixBrutKg) {
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

  public TarifAffinageFDetDTO prixBrutMin(BigDecimal prixBrutMin) {
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

  public TarifAffinageFDetDTO pctAuPerteMin(BigDecimal pctAuPerteMin) {
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

  public TarifAffinageFDetDTO pdsAuPerteMinKg(BigDecimal pdsAuPerteMinKg) {
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

  public TarifAffinageFDetDTO pdsAuPerteMinLot(BigDecimal pdsAuPerteMinLot) {
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

  public TarifAffinageFDetDTO pctAuDecote(BigDecimal pctAuDecote) {
    this.pctAuDecote = pctAuDecote;
    return this;
  }

  /**
   * Get pctAuDecote
   * @return pctAuDecote
   */
  @Valid 
  @JsonProperty("pctAuDecote")
  public BigDecimal getPctAuDecote() {
    return pctAuDecote;
  }

  public void setPctAuDecote(BigDecimal pctAuDecote) {
    this.pctAuDecote = pctAuDecote;
  }

  public TarifAffinageFDetDTO pctAgPerteMin(BigDecimal pctAgPerteMin) {
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

  public TarifAffinageFDetDTO pdsAgPerteMinKg(BigDecimal pdsAgPerteMinKg) {
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

  public TarifAffinageFDetDTO pdsAgPerteMinLot(BigDecimal pdsAgPerteMinLot) {
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

  public TarifAffinageFDetDTO pctAgDecote(BigDecimal pctAgDecote) {
    this.pctAgDecote = pctAgDecote;
    return this;
  }

  /**
   * Get pctAgDecote
   * @return pctAgDecote
   */
  @Valid 
  @JsonProperty("pctAgDecote")
  public BigDecimal getPctAgDecote() {
    return pctAgDecote;
  }

  public void setPctAgDecote(BigDecimal pctAgDecote) {
    this.pctAgDecote = pctAgDecote;
  }

  public TarifAffinageFDetDTO pctPtPerteMin(BigDecimal pctPtPerteMin) {
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

  public TarifAffinageFDetDTO pdsPtPerteMinKg(BigDecimal pdsPtPerteMinKg) {
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

  public TarifAffinageFDetDTO pdsPtPerteMinLot(BigDecimal pdsPtPerteMinLot) {
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

  public TarifAffinageFDetDTO pctPtDecote(BigDecimal pctPtDecote) {
    this.pctPtDecote = pctPtDecote;
    return this;
  }

  /**
   * Get pctPtDecote
   * @return pctPtDecote
   */
  @Valid 
  @JsonProperty("pctPtDecote")
  public BigDecimal getPctPtDecote() {
    return pctPtDecote;
  }

  public void setPctPtDecote(BigDecimal pctPtDecote) {
    this.pctPtDecote = pctPtDecote;
  }

  public TarifAffinageFDetDTO pctPdPerteMin(BigDecimal pctPdPerteMin) {
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

  public TarifAffinageFDetDTO pdsPdPerteMinKg(BigDecimal pdsPdPerteMinKg) {
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

  public TarifAffinageFDetDTO pdsPdPerteMinLot(BigDecimal pdsPdPerteMinLot) {
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

  public TarifAffinageFDetDTO pctPdDecote(BigDecimal pctPdDecote) {
    this.pctPdDecote = pctPdDecote;
    return this;
  }

  /**
   * Get pctPdDecote
   * @return pctPdDecote
   */
  @Valid 
  @JsonProperty("pctPdDecote")
  public BigDecimal getPctPdDecote() {
    return pctPdDecote;
  }

  public void setPctPdDecote(BigDecimal pctPdDecote) {
    this.pctPdDecote = pctPdDecote;
  }

  public TarifAffinageFDetDTO pctRhPerteMin(BigDecimal pctRhPerteMin) {
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

  public TarifAffinageFDetDTO pdsRhPerteMinKg(BigDecimal pdsRhPerteMinKg) {
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

  public TarifAffinageFDetDTO pdsRhPerteMinLot(BigDecimal pdsRhPerteMinLot) {
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

  public TarifAffinageFDetDTO pctRhDecote(BigDecimal pctRhDecote) {
    this.pctRhDecote = pctRhDecote;
    return this;
  }

  /**
   * Get pctRhDecote
   * @return pctRhDecote
   */
  @Valid 
  @JsonProperty("pctRhDecote")
  public BigDecimal getPctRhDecote() {
    return pctRhDecote;
  }

  public void setPctRhDecote(BigDecimal pctRhDecote) {
    this.pctRhDecote = pctRhDecote;
  }

  public TarifAffinageFDetDTO pctIrPerteMin(BigDecimal pctIrPerteMin) {
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

  public TarifAffinageFDetDTO pdsIrPerteMinKg(BigDecimal pdsIrPerteMinKg) {
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

  public TarifAffinageFDetDTO pdsIrPerteMinLot(BigDecimal pdsIrPerteMinLot) {
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

  public TarifAffinageFDetDTO pctIrDecote(BigDecimal pctIrDecote) {
    this.pctIrDecote = pctIrDecote;
    return this;
  }

  /**
   * Get pctIrDecote
   * @return pctIrDecote
   */
  @Valid 
  @JsonProperty("pctIrDecote")
  public BigDecimal getPctIrDecote() {
    return pctIrDecote;
  }

  public void setPctIrDecote(BigDecimal pctIrDecote) {
    this.pctIrDecote = pctIrDecote;
  }

  public TarifAffinageFDetDTO commentaire(String commentaire) {
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

  public TarifAffinageFDetDTO dateCreation(OffsetDateTime dateCreation) {
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

  public TarifAffinageFDetDTO idCreation(String idCreation) {
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

  public TarifAffinageFDetDTO dateModification(OffsetDateTime dateModification) {
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

  public TarifAffinageFDetDTO idModification(String idModification) {
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
    TarifAffinageFDetDTO tarifAffinageFDet = (TarifAffinageFDetDTO) o;
    return Objects.equals(this.id, tarifAffinageFDet.id) &&
        Objects.equals(this.codeTarifDet, tarifAffinageFDet.codeTarifDet) &&
        Objects.equals(this.codeTarif, tarifAffinageFDet.codeTarif) &&
        Objects.equals(this.pacThirdId, tarifAffinageFDet.pacThirdId) &&
        Objects.equals(this.pdsMin, tarifAffinageFDet.pdsMin) &&
        Objects.equals(this.pdsMax, tarifAffinageFDet.pdsMax) &&
        Objects.equals(this.prixBrutKg, tarifAffinageFDet.prixBrutKg) &&
        Objects.equals(this.prixBrutMin, tarifAffinageFDet.prixBrutMin) &&
        Objects.equals(this.pctAuPerteMin, tarifAffinageFDet.pctAuPerteMin) &&
        Objects.equals(this.pdsAuPerteMinKg, tarifAffinageFDet.pdsAuPerteMinKg) &&
        Objects.equals(this.pdsAuPerteMinLot, tarifAffinageFDet.pdsAuPerteMinLot) &&
        Objects.equals(this.pctAuDecote, tarifAffinageFDet.pctAuDecote) &&
        Objects.equals(this.pctAgPerteMin, tarifAffinageFDet.pctAgPerteMin) &&
        Objects.equals(this.pdsAgPerteMinKg, tarifAffinageFDet.pdsAgPerteMinKg) &&
        Objects.equals(this.pdsAgPerteMinLot, tarifAffinageFDet.pdsAgPerteMinLot) &&
        Objects.equals(this.pctAgDecote, tarifAffinageFDet.pctAgDecote) &&
        Objects.equals(this.pctPtPerteMin, tarifAffinageFDet.pctPtPerteMin) &&
        Objects.equals(this.pdsPtPerteMinKg, tarifAffinageFDet.pdsPtPerteMinKg) &&
        Objects.equals(this.pdsPtPerteMinLot, tarifAffinageFDet.pdsPtPerteMinLot) &&
        Objects.equals(this.pctPtDecote, tarifAffinageFDet.pctPtDecote) &&
        Objects.equals(this.pctPdPerteMin, tarifAffinageFDet.pctPdPerteMin) &&
        Objects.equals(this.pdsPdPerteMinKg, tarifAffinageFDet.pdsPdPerteMinKg) &&
        Objects.equals(this.pdsPdPerteMinLot, tarifAffinageFDet.pdsPdPerteMinLot) &&
        Objects.equals(this.pctPdDecote, tarifAffinageFDet.pctPdDecote) &&
        Objects.equals(this.pctRhPerteMin, tarifAffinageFDet.pctRhPerteMin) &&
        Objects.equals(this.pdsRhPerteMinKg, tarifAffinageFDet.pdsRhPerteMinKg) &&
        Objects.equals(this.pdsRhPerteMinLot, tarifAffinageFDet.pdsRhPerteMinLot) &&
        Objects.equals(this.pctRhDecote, tarifAffinageFDet.pctRhDecote) &&
        Objects.equals(this.pctIrPerteMin, tarifAffinageFDet.pctIrPerteMin) &&
        Objects.equals(this.pdsIrPerteMinKg, tarifAffinageFDet.pdsIrPerteMinKg) &&
        Objects.equals(this.pdsIrPerteMinLot, tarifAffinageFDet.pdsIrPerteMinLot) &&
        Objects.equals(this.pctIrDecote, tarifAffinageFDet.pctIrDecote) &&
        Objects.equals(this.commentaire, tarifAffinageFDet.commentaire) &&
        Objects.equals(this.dateCreation, tarifAffinageFDet.dateCreation) &&
        Objects.equals(this.idCreation, tarifAffinageFDet.idCreation) &&
        Objects.equals(this.dateModification, tarifAffinageFDet.dateModification) &&
        Objects.equals(this.idModification, tarifAffinageFDet.idModification);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, codeTarifDet, codeTarif, pacThirdId, pdsMin, pdsMax, prixBrutKg, prixBrutMin, pctAuPerteMin, pdsAuPerteMinKg, pdsAuPerteMinLot, pctAuDecote, pctAgPerteMin, pdsAgPerteMinKg, pdsAgPerteMinLot, pctAgDecote, pctPtPerteMin, pdsPtPerteMinKg, pdsPtPerteMinLot, pctPtDecote, pctPdPerteMin, pdsPdPerteMinKg, pdsPdPerteMinLot, pctPdDecote, pctRhPerteMin, pdsRhPerteMinKg, pdsRhPerteMinLot, pctRhDecote, pctIrPerteMin, pdsIrPerteMinKg, pdsIrPerteMinLot, pctIrDecote, commentaire, dateCreation, idCreation, dateModification, idModification);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TarifAffinageFDetDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    codeTarifDet: ").append(toIndentedString(codeTarifDet)).append("\n");
    sb.append("    codeTarif: ").append(toIndentedString(codeTarif)).append("\n");
    sb.append("    pacThirdId: ").append(toIndentedString(pacThirdId)).append("\n");
    sb.append("    pdsMin: ").append(toIndentedString(pdsMin)).append("\n");
    sb.append("    pdsMax: ").append(toIndentedString(pdsMax)).append("\n");
    sb.append("    prixBrutKg: ").append(toIndentedString(prixBrutKg)).append("\n");
    sb.append("    prixBrutMin: ").append(toIndentedString(prixBrutMin)).append("\n");
    sb.append("    pctAuPerteMin: ").append(toIndentedString(pctAuPerteMin)).append("\n");
    sb.append("    pdsAuPerteMinKg: ").append(toIndentedString(pdsAuPerteMinKg)).append("\n");
    sb.append("    pdsAuPerteMinLot: ").append(toIndentedString(pdsAuPerteMinLot)).append("\n");
    sb.append("    pctAuDecote: ").append(toIndentedString(pctAuDecote)).append("\n");
    sb.append("    pctAgPerteMin: ").append(toIndentedString(pctAgPerteMin)).append("\n");
    sb.append("    pdsAgPerteMinKg: ").append(toIndentedString(pdsAgPerteMinKg)).append("\n");
    sb.append("    pdsAgPerteMinLot: ").append(toIndentedString(pdsAgPerteMinLot)).append("\n");
    sb.append("    pctAgDecote: ").append(toIndentedString(pctAgDecote)).append("\n");
    sb.append("    pctPtPerteMin: ").append(toIndentedString(pctPtPerteMin)).append("\n");
    sb.append("    pdsPtPerteMinKg: ").append(toIndentedString(pdsPtPerteMinKg)).append("\n");
    sb.append("    pdsPtPerteMinLot: ").append(toIndentedString(pdsPtPerteMinLot)).append("\n");
    sb.append("    pctPtDecote: ").append(toIndentedString(pctPtDecote)).append("\n");
    sb.append("    pctPdPerteMin: ").append(toIndentedString(pctPdPerteMin)).append("\n");
    sb.append("    pdsPdPerteMinKg: ").append(toIndentedString(pdsPdPerteMinKg)).append("\n");
    sb.append("    pdsPdPerteMinLot: ").append(toIndentedString(pdsPdPerteMinLot)).append("\n");
    sb.append("    pctPdDecote: ").append(toIndentedString(pctPdDecote)).append("\n");
    sb.append("    pctRhPerteMin: ").append(toIndentedString(pctRhPerteMin)).append("\n");
    sb.append("    pdsRhPerteMinKg: ").append(toIndentedString(pdsRhPerteMinKg)).append("\n");
    sb.append("    pdsRhPerteMinLot: ").append(toIndentedString(pdsRhPerteMinLot)).append("\n");
    sb.append("    pctRhDecote: ").append(toIndentedString(pctRhDecote)).append("\n");
    sb.append("    pctIrPerteMin: ").append(toIndentedString(pctIrPerteMin)).append("\n");
    sb.append("    pdsIrPerteMinKg: ").append(toIndentedString(pdsIrPerteMinKg)).append("\n");
    sb.append("    pdsIrPerteMinLot: ").append(toIndentedString(pdsIrPerteMinLot)).append("\n");
    sb.append("    pctIrDecote: ").append(toIndentedString(pctIrDecote)).append("\n");
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

