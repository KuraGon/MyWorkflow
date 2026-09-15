package com.workflow.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.workflow.dto.PhoneContactDTO;
import java.util.UUID;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.*;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Contact métier avec identité (personne physique/morale) et jusqu&#39;à 3 numéros (références vers PhoneContact).
 */

@JsonTypeName("PhoneUserLite")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-07T10:14:28.616693700+01:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public class PhoneUserLiteDTO {

  private UUID id;

  private String firstname = null;

  private String lastname = null;

  private String denomination = null;

  private String company = null;

  private PhoneContactDTO phone;

  public PhoneUserLiteDTO id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  @Valid 
  @JsonProperty("id")
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public PhoneUserLiteDTO firstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  /**
   * Get firstname
   * @return firstname
   */
  
  @JsonProperty("firstname")
  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public PhoneUserLiteDTO lastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  /**
   * Get lastname
   * @return lastname
   */
  
  @JsonProperty("lastname")
  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public PhoneUserLiteDTO denomination(String denomination) {
    this.denomination = denomination;
    return this;
  }

  /**
   * Get denomination
   * @return denomination
   */
  
  @JsonProperty("denomination")
  public String getDenomination() {
    return denomination;
  }

  public void setDenomination(String denomination) {
    this.denomination = denomination;
  }

  public PhoneUserLiteDTO company(String company) {
    this.company = company;
    return this;
  }

  /**
   * Get company
   * @return company
   */
  
  @JsonProperty("company")
  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public PhoneUserLiteDTO phone(PhoneContactDTO phone) {
    this.phone = phone;
    return this;
  }

  /**
   * Get phone
   * @return phone
   */
  @Valid 
  @JsonProperty("phone")
  public PhoneContactDTO getPhone() {
    return phone;
  }

  public void setPhone(PhoneContactDTO phone) {
    this.phone = phone;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PhoneUserLiteDTO phoneUserLite = (PhoneUserLiteDTO) o;
    return Objects.equals(this.id, phoneUserLite.id) &&
        Objects.equals(this.firstname, phoneUserLite.firstname) &&
        Objects.equals(this.lastname, phoneUserLite.lastname) &&
        Objects.equals(this.denomination, phoneUserLite.denomination) &&
        Objects.equals(this.company, phoneUserLite.company) &&
        Objects.equals(this.phone, phoneUserLite.phone);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname, denomination, company, phone);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PhoneUserLiteDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    firstname: ").append(toIndentedString(firstname)).append("\n");
    sb.append("    lastname: ").append(toIndentedString(lastname)).append("\n");
    sb.append("    denomination: ").append(toIndentedString(denomination)).append("\n");
    sb.append("    company: ").append(toIndentedString(company)).append("\n");
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
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

