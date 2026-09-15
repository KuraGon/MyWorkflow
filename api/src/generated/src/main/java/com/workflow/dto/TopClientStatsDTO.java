package com.workflow.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.*;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * TopClientStatsDTO
 */

@JsonTypeName("TopClientStats")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-07T10:14:28.616693700+01:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public class TopClientStatsDTO {

  private String phoneNumber;

  private Long nbExchanges;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime lastInteraction;

  public TopClientStatsDTO phoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  /**
   * Get phoneNumber
   * @return phoneNumber
   */
  
  @JsonProperty("phoneNumber")
  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public TopClientStatsDTO nbExchanges(Long nbExchanges) {
    this.nbExchanges = nbExchanges;
    return this;
  }

  /**
   * Get nbExchanges
   * @return nbExchanges
   */
  
  @JsonProperty("nbExchanges")
  public Long getNbExchanges() {
    return nbExchanges;
  }

  public void setNbExchanges(Long nbExchanges) {
    this.nbExchanges = nbExchanges;
  }

  public TopClientStatsDTO lastInteraction(OffsetDateTime lastInteraction) {
    this.lastInteraction = lastInteraction;
    return this;
  }

  /**
   * Get lastInteraction
   * @return lastInteraction
   */
  @Valid 
  @JsonProperty("lastInteraction")
  public OffsetDateTime getLastInteraction() {
    return lastInteraction;
  }

  public void setLastInteraction(OffsetDateTime lastInteraction) {
    this.lastInteraction = lastInteraction;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TopClientStatsDTO topClientStats = (TopClientStatsDTO) o;
    return Objects.equals(this.phoneNumber, topClientStats.phoneNumber) &&
        Objects.equals(this.nbExchanges, topClientStats.nbExchanges) &&
        Objects.equals(this.lastInteraction, topClientStats.lastInteraction);
  }

  @Override
  public int hashCode() {
    return Objects.hash(phoneNumber, nbExchanges, lastInteraction);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TopClientStatsDTO {\n");
    sb.append("    phoneNumber: ").append(toIndentedString(phoneNumber)).append("\n");
    sb.append("    nbExchanges: ").append(toIndentedString(nbExchanges)).append("\n");
    sb.append("    lastInteraction: ").append(toIndentedString(lastInteraction)).append("\n");
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

