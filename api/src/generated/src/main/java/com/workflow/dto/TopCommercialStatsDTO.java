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
 * TopCommercialStatsDTO
 */

@JsonTypeName("TopCommercialStats")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-07T10:14:28.616693700+01:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public class TopCommercialStatsDTO {

  private String name;

  private Long nbCalls;

  private Long nbSms;

  private Long totalDuration;

  public TopCommercialStatsDTO name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   */
  
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public TopCommercialStatsDTO nbCalls(Long nbCalls) {
    this.nbCalls = nbCalls;
    return this;
  }

  /**
   * Get nbCalls
   * @return nbCalls
   */
  
  @JsonProperty("nbCalls")
  public Long getNbCalls() {
    return nbCalls;
  }

  public void setNbCalls(Long nbCalls) {
    this.nbCalls = nbCalls;
  }

  public TopCommercialStatsDTO nbSms(Long nbSms) {
    this.nbSms = nbSms;
    return this;
  }

  /**
   * Get nbSms
   * @return nbSms
   */
  
  @JsonProperty("nbSms")
  public Long getNbSms() {
    return nbSms;
  }

  public void setNbSms(Long nbSms) {
    this.nbSms = nbSms;
  }

  public TopCommercialStatsDTO totalDuration(Long totalDuration) {
    this.totalDuration = totalDuration;
    return this;
  }

  /**
   * Get totalDuration
   * @return totalDuration
   */
  
  @JsonProperty("totalDuration")
  public Long getTotalDuration() {
    return totalDuration;
  }

  public void setTotalDuration(Long totalDuration) {
    this.totalDuration = totalDuration;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TopCommercialStatsDTO topCommercialStats = (TopCommercialStatsDTO) o;
    return Objects.equals(this.name, topCommercialStats.name) &&
        Objects.equals(this.nbCalls, topCommercialStats.nbCalls) &&
        Objects.equals(this.nbSms, topCommercialStats.nbSms) &&
        Objects.equals(this.totalDuration, topCommercialStats.totalDuration);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, nbCalls, nbSms, totalDuration);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TopCommercialStatsDTO {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    nbCalls: ").append(toIndentedString(nbCalls)).append("\n");
    sb.append("    nbSms: ").append(toIndentedString(nbSms)).append("\n");
    sb.append("    totalDuration: ").append(toIndentedString(totalDuration)).append("\n");
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

