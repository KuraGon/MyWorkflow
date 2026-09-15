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
 * CallCorrespondentStatsDTO
 */

@JsonTypeName("CallCorrespondentStats")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-07T10:14:28.616693700+01:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public class CallCorrespondentStatsDTO {

  private String number;

  private String firstname = null;

  private String lastname = null;

  private String company = null;

  private String displayName = null;

  private Long callCount;

  private Long totalDurationSec;

  private Float totalCostHt;

  public CallCorrespondentStatsDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CallCorrespondentStatsDTO(String number, Long callCount, Long totalDurationSec, Float totalCostHt) {
    this.number = number;
    this.callCount = callCount;
    this.totalDurationSec = totalDurationSec;
    this.totalCostHt = totalCostHt;
  }

  public CallCorrespondentStatsDTO number(String number) {
    this.number = number;
    return this;
  }

  /**
   * Get number
   * @return number
   */
  @NotNull 
  @JsonProperty("number")
  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public CallCorrespondentStatsDTO firstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  /**
   * Prénom issu de phone_user si disponible.
   * @return firstname
   */
  
  @JsonProperty("firstname")
  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public CallCorrespondentStatsDTO lastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  /**
   * Nom issu de phone_user si disponible.
   * @return lastname
   */
  
  @JsonProperty("lastname")
  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public CallCorrespondentStatsDTO company(String company) {
    this.company = company;
    return this;
  }

  /**
   * Company ou dénomination issue de phone_user si disponible.
   * @return company
   */
  
  @JsonProperty("company")
  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public CallCorrespondentStatsDTO displayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

  /**
   * Nom d'affichage (si calculé côté API/front).
   * @return displayName
   */
  
  @JsonProperty("displayName")
  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public CallCorrespondentStatsDTO callCount(Long callCount) {
    this.callCount = callCount;
    return this;
  }

  /**
   * Get callCount
   * @return callCount
   */
  @NotNull 
  @JsonProperty("callCount")
  public Long getCallCount() {
    return callCount;
  }

  public void setCallCount(Long callCount) {
    this.callCount = callCount;
  }

  public CallCorrespondentStatsDTO totalDurationSec(Long totalDurationSec) {
    this.totalDurationSec = totalDurationSec;
    return this;
  }

  /**
   * Get totalDurationSec
   * @return totalDurationSec
   */
  @NotNull 
  @JsonProperty("totalDurationSec")
  public Long getTotalDurationSec() {
    return totalDurationSec;
  }

  public void setTotalDurationSec(Long totalDurationSec) {
    this.totalDurationSec = totalDurationSec;
  }

  public CallCorrespondentStatsDTO totalCostHt(Float totalCostHt) {
    this.totalCostHt = totalCostHt;
    return this;
  }

  /**
   * Get totalCostHt
   * @return totalCostHt
   */
  @NotNull 
  @JsonProperty("totalCostHt")
  public Float getTotalCostHt() {
    return totalCostHt;
  }

  public void setTotalCostHt(Float totalCostHt) {
    this.totalCostHt = totalCostHt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CallCorrespondentStatsDTO callCorrespondentStats = (CallCorrespondentStatsDTO) o;
    return Objects.equals(this.number, callCorrespondentStats.number) &&
        Objects.equals(this.firstname, callCorrespondentStats.firstname) &&
        Objects.equals(this.lastname, callCorrespondentStats.lastname) &&
        Objects.equals(this.company, callCorrespondentStats.company) &&
        Objects.equals(this.displayName, callCorrespondentStats.displayName) &&
        Objects.equals(this.callCount, callCorrespondentStats.callCount) &&
        Objects.equals(this.totalDurationSec, callCorrespondentStats.totalDurationSec) &&
        Objects.equals(this.totalCostHt, callCorrespondentStats.totalCostHt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(number, firstname, lastname, company, displayName, callCount, totalDurationSec, totalCostHt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CallCorrespondentStatsDTO {\n");
    sb.append("    number: ").append(toIndentedString(number)).append("\n");
    sb.append("    firstname: ").append(toIndentedString(firstname)).append("\n");
    sb.append("    lastname: ").append(toIndentedString(lastname)).append("\n");
    sb.append("    company: ").append(toIndentedString(company)).append("\n");
    sb.append("    displayName: ").append(toIndentedString(displayName)).append("\n");
    sb.append("    callCount: ").append(toIndentedString(callCount)).append("\n");
    sb.append("    totalDurationSec: ").append(toIndentedString(totalDurationSec)).append("\n");
    sb.append("    totalCostHt: ").append(toIndentedString(totalCostHt)).append("\n");
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

