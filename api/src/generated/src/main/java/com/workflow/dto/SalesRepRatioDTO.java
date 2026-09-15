package com.workflow.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.*;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Répartition des interactions (clients vs interne vs inconnu) sur la période.
 */

@JsonTypeName("SalesRepRatio")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-07T10:14:28.616693700+01:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public class SalesRepRatioDTO {

  private Long totalInteractions;

  private Long clientInteractions;

  private Long internalInteractions;

  private Long unknownInteractions;

  private Double clientPct;

  private Double internalPct;

  private Double unknownPct;

  public SalesRepRatioDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public SalesRepRatioDTO(Long totalInteractions, Long clientInteractions, Long internalInteractions, Long unknownInteractions, Double clientPct, Double internalPct, Double unknownPct) {
    this.totalInteractions = totalInteractions;
    this.clientInteractions = clientInteractions;
    this.internalInteractions = internalInteractions;
    this.unknownInteractions = unknownInteractions;
    this.clientPct = clientPct;
    this.internalPct = internalPct;
    this.unknownPct = unknownPct;
  }

  public SalesRepRatioDTO totalInteractions(Long totalInteractions) {
    this.totalInteractions = totalInteractions;
    return this;
  }

  /**
   * Get totalInteractions
   * @return totalInteractions
   */
  @NotNull 
  @JsonProperty("totalInteractions")
  public Long getTotalInteractions() {
    return totalInteractions;
  }

  public void setTotalInteractions(Long totalInteractions) {
    this.totalInteractions = totalInteractions;
  }

  public SalesRepRatioDTO clientInteractions(Long clientInteractions) {
    this.clientInteractions = clientInteractions;
    return this;
  }

  /**
   * Get clientInteractions
   * @return clientInteractions
   */
  @NotNull 
  @JsonProperty("clientInteractions")
  public Long getClientInteractions() {
    return clientInteractions;
  }

  public void setClientInteractions(Long clientInteractions) {
    this.clientInteractions = clientInteractions;
  }

  public SalesRepRatioDTO internalInteractions(Long internalInteractions) {
    this.internalInteractions = internalInteractions;
    return this;
  }

  /**
   * Get internalInteractions
   * @return internalInteractions
   */
  @NotNull 
  @JsonProperty("internalInteractions")
  public Long getInternalInteractions() {
    return internalInteractions;
  }

  public void setInternalInteractions(Long internalInteractions) {
    this.internalInteractions = internalInteractions;
  }

  public SalesRepRatioDTO unknownInteractions(Long unknownInteractions) {
    this.unknownInteractions = unknownInteractions;
    return this;
  }

  /**
   * Get unknownInteractions
   * @return unknownInteractions
   */
  @NotNull 
  @JsonProperty("unknownInteractions")
  public Long getUnknownInteractions() {
    return unknownInteractions;
  }

  public void setUnknownInteractions(Long unknownInteractions) {
    this.unknownInteractions = unknownInteractions;
  }

  public SalesRepRatioDTO clientPct(Double clientPct) {
    this.clientPct = clientPct;
    return this;
  }

  /**
   * Pourcentage 0..100
   * @return clientPct
   */
  @NotNull 
  @JsonProperty("clientPct")
  public Double getClientPct() {
    return clientPct;
  }

  public void setClientPct(Double clientPct) {
    this.clientPct = clientPct;
  }

  public SalesRepRatioDTO internalPct(Double internalPct) {
    this.internalPct = internalPct;
    return this;
  }

  /**
   * Pourcentage 0..100
   * @return internalPct
   */
  @NotNull 
  @JsonProperty("internalPct")
  public Double getInternalPct() {
    return internalPct;
  }

  public void setInternalPct(Double internalPct) {
    this.internalPct = internalPct;
  }

  public SalesRepRatioDTO unknownPct(Double unknownPct) {
    this.unknownPct = unknownPct;
    return this;
  }

  /**
   * Pourcentage 0..100
   * @return unknownPct
   */
  @NotNull 
  @JsonProperty("unknownPct")
  public Double getUnknownPct() {
    return unknownPct;
  }

  public void setUnknownPct(Double unknownPct) {
    this.unknownPct = unknownPct;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SalesRepRatioDTO salesRepRatio = (SalesRepRatioDTO) o;
    return Objects.equals(this.totalInteractions, salesRepRatio.totalInteractions) &&
        Objects.equals(this.clientInteractions, salesRepRatio.clientInteractions) &&
        Objects.equals(this.internalInteractions, salesRepRatio.internalInteractions) &&
        Objects.equals(this.unknownInteractions, salesRepRatio.unknownInteractions) &&
        Objects.equals(this.clientPct, salesRepRatio.clientPct) &&
        Objects.equals(this.internalPct, salesRepRatio.internalPct) &&
        Objects.equals(this.unknownPct, salesRepRatio.unknownPct);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalInteractions, clientInteractions, internalInteractions, unknownInteractions, clientPct, internalPct, unknownPct);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SalesRepRatioDTO {\n");
    sb.append("    totalInteractions: ").append(toIndentedString(totalInteractions)).append("\n");
    sb.append("    clientInteractions: ").append(toIndentedString(clientInteractions)).append("\n");
    sb.append("    internalInteractions: ").append(toIndentedString(internalInteractions)).append("\n");
    sb.append("    unknownInteractions: ").append(toIndentedString(unknownInteractions)).append("\n");
    sb.append("    clientPct: ").append(toIndentedString(clientPct)).append("\n");
    sb.append("    internalPct: ").append(toIndentedString(internalPct)).append("\n");
    sb.append("    unknownPct: ").append(toIndentedString(unknownPct)).append("\n");
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

