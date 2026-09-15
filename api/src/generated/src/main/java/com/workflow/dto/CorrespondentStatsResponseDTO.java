package com.workflow.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.workflow.dto.CallCorrespondentStatsDTO;
import com.workflow.dto.SmsCorrespondentStatsDTO;
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
 * CorrespondentStatsResponseDTO
 */

@JsonTypeName("CorrespondentStatsResponse")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-07T10:14:28.616693700+01:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public class CorrespondentStatsResponseDTO {

  @Valid
  private List<@Valid CallCorrespondentStatsDTO> callStats = new ArrayList<>();

  @Valid
  private List<@Valid SmsCorrespondentStatsDTO> smsStats = new ArrayList<>();

  public CorrespondentStatsResponseDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CorrespondentStatsResponseDTO(List<@Valid CallCorrespondentStatsDTO> callStats, List<@Valid SmsCorrespondentStatsDTO> smsStats) {
    this.callStats = callStats;
    this.smsStats = smsStats;
  }

  public CorrespondentStatsResponseDTO callStats(List<@Valid CallCorrespondentStatsDTO> callStats) {
    this.callStats = callStats;
    return this;
  }

  public CorrespondentStatsResponseDTO addCallStatsItem(CallCorrespondentStatsDTO callStatsItem) {
    if (this.callStats == null) {
      this.callStats = new ArrayList<>();
    }
    this.callStats.add(callStatsItem);
    return this;
  }

  /**
   * Get callStats
   * @return callStats
   */
  @NotNull @Valid 
  @JsonProperty("callStats")
  public List<@Valid CallCorrespondentStatsDTO> getCallStats() {
    return callStats;
  }

  public void setCallStats(List<@Valid CallCorrespondentStatsDTO> callStats) {
    this.callStats = callStats;
  }

  public CorrespondentStatsResponseDTO smsStats(List<@Valid SmsCorrespondentStatsDTO> smsStats) {
    this.smsStats = smsStats;
    return this;
  }

  public CorrespondentStatsResponseDTO addSmsStatsItem(SmsCorrespondentStatsDTO smsStatsItem) {
    if (this.smsStats == null) {
      this.smsStats = new ArrayList<>();
    }
    this.smsStats.add(smsStatsItem);
    return this;
  }

  /**
   * Get smsStats
   * @return smsStats
   */
  @NotNull @Valid 
  @JsonProperty("smsStats")
  public List<@Valid SmsCorrespondentStatsDTO> getSmsStats() {
    return smsStats;
  }

  public void setSmsStats(List<@Valid SmsCorrespondentStatsDTO> smsStats) {
    this.smsStats = smsStats;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CorrespondentStatsResponseDTO correspondentStatsResponse = (CorrespondentStatsResponseDTO) o;
    return Objects.equals(this.callStats, correspondentStatsResponse.callStats) &&
        Objects.equals(this.smsStats, correspondentStatsResponse.smsStats);
  }

  @Override
  public int hashCode() {
    return Objects.hash(callStats, smsStats);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CorrespondentStatsResponseDTO {\n");
    sb.append("    callStats: ").append(toIndentedString(callStats)).append("\n");
    sb.append("    smsStats: ").append(toIndentedString(smsStats)).append("\n");
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

