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
 * SmsCorrespondentStatsDTO
 */

@JsonTypeName("SmsCorrespondentStats")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-07T10:14:28.616693700+01:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public class SmsCorrespondentStatsDTO {

  private String number;

  private String firstname = null;

  private String lastname = null;

  private String company = null;

  private String displayName = null;

  private Long smsCount;

  private Float totalCostHt;

  public SmsCorrespondentStatsDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public SmsCorrespondentStatsDTO(String number, Long smsCount, Float totalCostHt) {
    this.number = number;
    this.smsCount = smsCount;
    this.totalCostHt = totalCostHt;
  }

  public SmsCorrespondentStatsDTO number(String number) {
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

  public SmsCorrespondentStatsDTO firstname(String firstname) {
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

  public SmsCorrespondentStatsDTO lastname(String lastname) {
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

  public SmsCorrespondentStatsDTO company(String company) {
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

  public SmsCorrespondentStatsDTO displayName(String displayName) {
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

  public SmsCorrespondentStatsDTO smsCount(Long smsCount) {
    this.smsCount = smsCount;
    return this;
  }

  /**
   * Get smsCount
   * @return smsCount
   */
  @NotNull 
  @JsonProperty("smsCount")
  public Long getSmsCount() {
    return smsCount;
  }

  public void setSmsCount(Long smsCount) {
    this.smsCount = smsCount;
  }

  public SmsCorrespondentStatsDTO totalCostHt(Float totalCostHt) {
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
    SmsCorrespondentStatsDTO smsCorrespondentStats = (SmsCorrespondentStatsDTO) o;
    return Objects.equals(this.number, smsCorrespondentStats.number) &&
        Objects.equals(this.firstname, smsCorrespondentStats.firstname) &&
        Objects.equals(this.lastname, smsCorrespondentStats.lastname) &&
        Objects.equals(this.company, smsCorrespondentStats.company) &&
        Objects.equals(this.displayName, smsCorrespondentStats.displayName) &&
        Objects.equals(this.smsCount, smsCorrespondentStats.smsCount) &&
        Objects.equals(this.totalCostHt, smsCorrespondentStats.totalCostHt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(number, firstname, lastname, company, displayName, smsCount, totalCostHt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SmsCorrespondentStatsDTO {\n");
    sb.append("    number: ").append(toIndentedString(number)).append("\n");
    sb.append("    firstname: ").append(toIndentedString(firstname)).append("\n");
    sb.append("    lastname: ").append(toIndentedString(lastname)).append("\n");
    sb.append("    company: ").append(toIndentedString(company)).append("\n");
    sb.append("    displayName: ").append(toIndentedString(displayName)).append("\n");
    sb.append("    smsCount: ").append(toIndentedString(smsCount)).append("\n");
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

