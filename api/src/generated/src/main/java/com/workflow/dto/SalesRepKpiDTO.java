package com.workflow.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.workflow.dto.SalesRepRatioDTO;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.*;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * SalesRepKpiDTO
 */

@JsonTypeName("SalesRepKpi")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-07T10:14:28.616693700+01:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public class SalesRepKpiDTO {

  private Long totalCallDurationSec;

  private Long totalCalls;

  private Long totalCallsWeekEndAndNight;

  private Long uniqueProspects;

  private SalesRepRatioDTO ratio;

  public SalesRepKpiDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public SalesRepKpiDTO(Long totalCallDurationSec, Long totalCalls, Long uniqueProspects, SalesRepRatioDTO ratio) {
    this.totalCallDurationSec = totalCallDurationSec;
    this.totalCalls = totalCalls;
    this.uniqueProspects = uniqueProspects;
    this.ratio = ratio;
  }

  public SalesRepKpiDTO totalCallDurationSec(Long totalCallDurationSec) {
    this.totalCallDurationSec = totalCallDurationSec;
    return this;
  }

  /**
   * Temps d'appel cumulé en secondes
   * @return totalCallDurationSec
   */
  @NotNull 
  @JsonProperty("totalCallDurationSec")
  public Long getTotalCallDurationSec() {
    return totalCallDurationSec;
  }

  public void setTotalCallDurationSec(Long totalCallDurationSec) {
    this.totalCallDurationSec = totalCallDurationSec;
  }

  public SalesRepKpiDTO totalCalls(Long totalCalls) {
    this.totalCalls = totalCalls;
    return this;
  }

  /**
   * Nombre d'appels (hors SMS)
   * @return totalCalls
   */
  @NotNull 
  @JsonProperty("totalCalls")
  public Long getTotalCalls() {
    return totalCalls;
  }

  public void setTotalCalls(Long totalCalls) {
    this.totalCalls = totalCalls;
  }

  public SalesRepKpiDTO totalCallsWeekEndAndNight(Long totalCallsWeekEndAndNight) {
    this.totalCallsWeekEndAndNight = totalCallsWeekEndAndNight;
    return this;
  }

  /**
   * Get totalCallsWeekEndAndNight
   * @return totalCallsWeekEndAndNight
   */
  
  @JsonProperty("totalCallsWeekEndAndNight")
  public Long getTotalCallsWeekEndAndNight() {
    return totalCallsWeekEndAndNight;
  }

  public void setTotalCallsWeekEndAndNight(Long totalCallsWeekEndAndNight) {
    this.totalCallsWeekEndAndNight = totalCallsWeekEndAndNight;
  }

  public SalesRepKpiDTO uniqueProspects(Long uniqueProspects) {
    this.uniqueProspects = uniqueProspects;
    return this;
  }

  /**
   * Nombre de correspondants uniques (hors internes)
   * @return uniqueProspects
   */
  @NotNull 
  @JsonProperty("uniqueProspects")
  public Long getUniqueProspects() {
    return uniqueProspects;
  }

  public void setUniqueProspects(Long uniqueProspects) {
    this.uniqueProspects = uniqueProspects;
  }

  public SalesRepKpiDTO ratio(SalesRepRatioDTO ratio) {
    this.ratio = ratio;
    return this;
  }

  /**
   * Get ratio
   * @return ratio
   */
  @NotNull @Valid 
  @JsonProperty("ratio")
  public SalesRepRatioDTO getRatio() {
    return ratio;
  }

  public void setRatio(SalesRepRatioDTO ratio) {
    this.ratio = ratio;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SalesRepKpiDTO salesRepKpi = (SalesRepKpiDTO) o;
    return Objects.equals(this.totalCallDurationSec, salesRepKpi.totalCallDurationSec) &&
        Objects.equals(this.totalCalls, salesRepKpi.totalCalls) &&
        Objects.equals(this.totalCallsWeekEndAndNight, salesRepKpi.totalCallsWeekEndAndNight) &&
        Objects.equals(this.uniqueProspects, salesRepKpi.uniqueProspects) &&
        Objects.equals(this.ratio, salesRepKpi.ratio);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalCallDurationSec, totalCalls, totalCallsWeekEndAndNight, uniqueProspects, ratio);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SalesRepKpiDTO {\n");
    sb.append("    totalCallDurationSec: ").append(toIndentedString(totalCallDurationSec)).append("\n");
    sb.append("    totalCalls: ").append(toIndentedString(totalCalls)).append("\n");
    sb.append("    totalCallsWeekEndAndNight: ").append(toIndentedString(totalCallsWeekEndAndNight)).append("\n");
    sb.append("    uniqueProspects: ").append(toIndentedString(uniqueProspects)).append("\n");
    sb.append("    ratio: ").append(toIndentedString(ratio)).append("\n");
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

