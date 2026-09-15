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
 * GlobalKpiDTO
 */

@JsonTypeName("GlobalKpi")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-07T10:14:28.616693700+01:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public class GlobalKpiDTO {

  private Long totalCalls;

  private Long totalSms;

  private Long totalCallsWeekEndAndNight;

  private Long totalDurationSec;

  private Long uniqueContactsCount;

  public GlobalKpiDTO totalCalls(Long totalCalls) {
    this.totalCalls = totalCalls;
    return this;
  }

  /**
   * Get totalCalls
   * @return totalCalls
   */
  
  @JsonProperty("totalCalls")
  public Long getTotalCalls() {
    return totalCalls;
  }

  public void setTotalCalls(Long totalCalls) {
    this.totalCalls = totalCalls;
  }

  public GlobalKpiDTO totalSms(Long totalSms) {
    this.totalSms = totalSms;
    return this;
  }

  /**
   * Get totalSms
   * @return totalSms
   */
  
  @JsonProperty("totalSms")
  public Long getTotalSms() {
    return totalSms;
  }

  public void setTotalSms(Long totalSms) {
    this.totalSms = totalSms;
  }

  public GlobalKpiDTO totalCallsWeekEndAndNight(Long totalCallsWeekEndAndNight) {
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

  public GlobalKpiDTO totalDurationSec(Long totalDurationSec) {
    this.totalDurationSec = totalDurationSec;
    return this;
  }

  /**
   * Get totalDurationSec
   * @return totalDurationSec
   */
  
  @JsonProperty("totalDurationSec")
  public Long getTotalDurationSec() {
    return totalDurationSec;
  }

  public void setTotalDurationSec(Long totalDurationSec) {
    this.totalDurationSec = totalDurationSec;
  }

  public GlobalKpiDTO uniqueContactsCount(Long uniqueContactsCount) {
    this.uniqueContactsCount = uniqueContactsCount;
    return this;
  }

  /**
   * Get uniqueContactsCount
   * @return uniqueContactsCount
   */
  
  @JsonProperty("uniqueContactsCount")
  public Long getUniqueContactsCount() {
    return uniqueContactsCount;
  }

  public void setUniqueContactsCount(Long uniqueContactsCount) {
    this.uniqueContactsCount = uniqueContactsCount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GlobalKpiDTO globalKpi = (GlobalKpiDTO) o;
    return Objects.equals(this.totalCalls, globalKpi.totalCalls) &&
        Objects.equals(this.totalSms, globalKpi.totalSms) &&
        Objects.equals(this.totalCallsWeekEndAndNight, globalKpi.totalCallsWeekEndAndNight) &&
        Objects.equals(this.totalDurationSec, globalKpi.totalDurationSec) &&
        Objects.equals(this.uniqueContactsCount, globalKpi.uniqueContactsCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalCalls, totalSms, totalCallsWeekEndAndNight, totalDurationSec, uniqueContactsCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GlobalKpiDTO {\n");
    sb.append("    totalCalls: ").append(toIndentedString(totalCalls)).append("\n");
    sb.append("    totalSms: ").append(toIndentedString(totalSms)).append("\n");
    sb.append("    totalCallsWeekEndAndNight: ").append(toIndentedString(totalCallsWeekEndAndNight)).append("\n");
    sb.append("    totalDurationSec: ").append(toIndentedString(totalDurationSec)).append("\n");
    sb.append("    uniqueContactsCount: ").append(toIndentedString(uniqueContactsCount)).append("\n");
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

