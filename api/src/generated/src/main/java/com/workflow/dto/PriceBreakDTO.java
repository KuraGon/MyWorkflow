package com.workflow.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.*;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Une seule ligne/tranche de prix dans une grille (basé sur _P_AFF et _F_DET)
 */

@JsonTypeName("PriceBreak")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-07T10:14:28.616693700+01:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public class PriceBreakDTO {

  private Long id;

  private BigDecimal pdsMin;

  private BigDecimal pdsMax;

  private BigDecimal prixBrutKg;

  private BigDecimal prixBrutMin;

  private BigDecimal pctAuPerteMin = null;

  private BigDecimal pdsAuPerteMinKg = null;

  private BigDecimal pdsAuPerteMinLot = null;

  private BigDecimal pctAuDecote = null;

  private BigDecimal pctAgPerteMin = null;

  private BigDecimal pdsAgPerteMinKg = null;

  private BigDecimal pdsAgPerteMinLot = null;

  private BigDecimal pctAgDecote = null;

  private BigDecimal pctPtPerteMin = null;

  private BigDecimal pdsPtPerteMinKg = null;

  private BigDecimal pdsPtPerteMinLot = null;

  private BigDecimal pctPtDecote = null;

  private BigDecimal pctPdPerteMin = null;

  private BigDecimal pdsPdPerteMinKg = null;

  private BigDecimal pdsPdPerteMinLot = null;

  private BigDecimal pctPdDecote = null;

  private BigDecimal pctRhPerteMin = null;

  private BigDecimal pdsRhPerteMinKg = null;

  private BigDecimal pdsRhPerteMinLot = null;

  private BigDecimal pctRhDecote = null;

  private BigDecimal pctIrPerteMin = null;

  private BigDecimal pdsIrPerteMinKg = null;

  private BigDecimal pdsIrPerteMinLot = null;

  private BigDecimal pctIrDecote = null;

  public PriceBreakDTO id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * ID de la ligne de prix (ex: 4048, 2653)
   * @return id
   */
  
  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public PriceBreakDTO pdsMin(BigDecimal pdsMin) {
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

  public PriceBreakDTO pdsMax(BigDecimal pdsMax) {
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

  public PriceBreakDTO prixBrutKg(BigDecimal prixBrutKg) {
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

  public PriceBreakDTO prixBrutMin(BigDecimal prixBrutMin) {
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

  public PriceBreakDTO pctAuPerteMin(BigDecimal pctAuPerteMin) {
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

  public PriceBreakDTO pdsAuPerteMinKg(BigDecimal pdsAuPerteMinKg) {
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

  public PriceBreakDTO pdsAuPerteMinLot(BigDecimal pdsAuPerteMinLot) {
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

  public PriceBreakDTO pctAuDecote(BigDecimal pctAuDecote) {
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

  public PriceBreakDTO pctAgPerteMin(BigDecimal pctAgPerteMin) {
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

  public PriceBreakDTO pdsAgPerteMinKg(BigDecimal pdsAgPerteMinKg) {
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

  public PriceBreakDTO pdsAgPerteMinLot(BigDecimal pdsAgPerteMinLot) {
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

  public PriceBreakDTO pctAgDecote(BigDecimal pctAgDecote) {
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

  public PriceBreakDTO pctPtPerteMin(BigDecimal pctPtPerteMin) {
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

  public PriceBreakDTO pdsPtPerteMinKg(BigDecimal pdsPtPerteMinKg) {
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

  public PriceBreakDTO pdsPtPerteMinLot(BigDecimal pdsPtPerteMinLot) {
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

  public PriceBreakDTO pctPtDecote(BigDecimal pctPtDecote) {
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

  public PriceBreakDTO pctPdPerteMin(BigDecimal pctPdPerteMin) {
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

  public PriceBreakDTO pdsPdPerteMinKg(BigDecimal pdsPdPerteMinKg) {
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

  public PriceBreakDTO pdsPdPerteMinLot(BigDecimal pdsPdPerteMinLot) {
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

  public PriceBreakDTO pctPdDecote(BigDecimal pctPdDecote) {
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

  public PriceBreakDTO pctRhPerteMin(BigDecimal pctRhPerteMin) {
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

  public PriceBreakDTO pdsRhPerteMinKg(BigDecimal pdsRhPerteMinKg) {
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

  public PriceBreakDTO pdsRhPerteMinLot(BigDecimal pdsRhPerteMinLot) {
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

  public PriceBreakDTO pctRhDecote(BigDecimal pctRhDecote) {
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

  public PriceBreakDTO pctIrPerteMin(BigDecimal pctIrPerteMin) {
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

  public PriceBreakDTO pdsIrPerteMinKg(BigDecimal pdsIrPerteMinKg) {
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

  public PriceBreakDTO pdsIrPerteMinLot(BigDecimal pdsIrPerteMinLot) {
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

  public PriceBreakDTO pctIrDecote(BigDecimal pctIrDecote) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PriceBreakDTO priceBreak = (PriceBreakDTO) o;
    return Objects.equals(this.id, priceBreak.id) &&
        Objects.equals(this.pdsMin, priceBreak.pdsMin) &&
        Objects.equals(this.pdsMax, priceBreak.pdsMax) &&
        Objects.equals(this.prixBrutKg, priceBreak.prixBrutKg) &&
        Objects.equals(this.prixBrutMin, priceBreak.prixBrutMin) &&
        Objects.equals(this.pctAuPerteMin, priceBreak.pctAuPerteMin) &&
        Objects.equals(this.pdsAuPerteMinKg, priceBreak.pdsAuPerteMinKg) &&
        Objects.equals(this.pdsAuPerteMinLot, priceBreak.pdsAuPerteMinLot) &&
        Objects.equals(this.pctAuDecote, priceBreak.pctAuDecote) &&
        Objects.equals(this.pctAgPerteMin, priceBreak.pctAgPerteMin) &&
        Objects.equals(this.pdsAgPerteMinKg, priceBreak.pdsAgPerteMinKg) &&
        Objects.equals(this.pdsAgPerteMinLot, priceBreak.pdsAgPerteMinLot) &&
        Objects.equals(this.pctAgDecote, priceBreak.pctAgDecote) &&
        Objects.equals(this.pctPtPerteMin, priceBreak.pctPtPerteMin) &&
        Objects.equals(this.pdsPtPerteMinKg, priceBreak.pdsPtPerteMinKg) &&
        Objects.equals(this.pdsPtPerteMinLot, priceBreak.pdsPtPerteMinLot) &&
        Objects.equals(this.pctPtDecote, priceBreak.pctPtDecote) &&
        Objects.equals(this.pctPdPerteMin, priceBreak.pctPdPerteMin) &&
        Objects.equals(this.pdsPdPerteMinKg, priceBreak.pdsPdPerteMinKg) &&
        Objects.equals(this.pdsPdPerteMinLot, priceBreak.pdsPdPerteMinLot) &&
        Objects.equals(this.pctPdDecote, priceBreak.pctPdDecote) &&
        Objects.equals(this.pctRhPerteMin, priceBreak.pctRhPerteMin) &&
        Objects.equals(this.pdsRhPerteMinKg, priceBreak.pdsRhPerteMinKg) &&
        Objects.equals(this.pdsRhPerteMinLot, priceBreak.pdsRhPerteMinLot) &&
        Objects.equals(this.pctRhDecote, priceBreak.pctRhDecote) &&
        Objects.equals(this.pctIrPerteMin, priceBreak.pctIrPerteMin) &&
        Objects.equals(this.pdsIrPerteMinKg, priceBreak.pdsIrPerteMinKg) &&
        Objects.equals(this.pdsIrPerteMinLot, priceBreak.pdsIrPerteMinLot) &&
        Objects.equals(this.pctIrDecote, priceBreak.pctIrDecote);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, pdsMin, pdsMax, prixBrutKg, prixBrutMin, pctAuPerteMin, pdsAuPerteMinKg, pdsAuPerteMinLot, pctAuDecote, pctAgPerteMin, pdsAgPerteMinKg, pdsAgPerteMinLot, pctAgDecote, pctPtPerteMin, pdsPtPerteMinKg, pdsPtPerteMinLot, pctPtDecote, pctPdPerteMin, pdsPdPerteMinKg, pdsPdPerteMinLot, pctPdDecote, pctRhPerteMin, pdsRhPerteMinKg, pdsRhPerteMinLot, pctRhDecote, pctIrPerteMin, pdsIrPerteMinKg, pdsIrPerteMinLot, pctIrDecote);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PriceBreakDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

