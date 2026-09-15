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

@JsonTypeName("PhoneUser")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-07T10:14:28.616693700+01:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public class PhoneUserDTO {

  private UUID id;

  private String firstname = null;

  private String lastname = null;

  private String denomination = null;

  private String company = null;

  private PhoneContactDTO phonePro;

  private PhoneContactDTO phonePerso;

  private PhoneContactDTO phoneTelavox;

  private PhoneContactDTO phoneOther;

  public PhoneUserDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public PhoneUserDTO(UUID id) {
    this.id = id;
  }

  public PhoneUserDTO id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  @NotNull @Valid 
  @JsonProperty("id")
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public PhoneUserDTO firstname(String firstname) {
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

  public PhoneUserDTO lastname(String lastname) {
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

  public PhoneUserDTO denomination(String denomination) {
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

  public PhoneUserDTO company(String company) {
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

  public PhoneUserDTO phonePro(PhoneContactDTO phonePro) {
    this.phonePro = phonePro;
    return this;
  }

  /**
   * Get phonePro
   * @return phonePro
   */
  @Valid 
  @JsonProperty("phonePro")
  public PhoneContactDTO getPhonePro() {
    return phonePro;
  }

  public void setPhonePro(PhoneContactDTO phonePro) {
    this.phonePro = phonePro;
  }

  public PhoneUserDTO phonePerso(PhoneContactDTO phonePerso) {
    this.phonePerso = phonePerso;
    return this;
  }

  /**
   * Get phonePerso
   * @return phonePerso
   */
  @Valid 
  @JsonProperty("phonePerso")
  public PhoneContactDTO getPhonePerso() {
    return phonePerso;
  }

  public void setPhonePerso(PhoneContactDTO phonePerso) {
    this.phonePerso = phonePerso;
  }

  public PhoneUserDTO phoneTelavox(PhoneContactDTO phoneTelavox) {
    this.phoneTelavox = phoneTelavox;
    return this;
  }

  /**
   * Get phoneTelavox
   * @return phoneTelavox
   */
  @Valid 
  @JsonProperty("phoneTelavox")
  public PhoneContactDTO getPhoneTelavox() {
    return phoneTelavox;
  }

  public void setPhoneTelavox(PhoneContactDTO phoneTelavox) {
    this.phoneTelavox = phoneTelavox;
  }

  public PhoneUserDTO phoneOther(PhoneContactDTO phoneOther) {
    this.phoneOther = phoneOther;
    return this;
  }

  /**
   * Get phoneOther
   * @return phoneOther
   */
  @Valid 
  @JsonProperty("phoneOther")
  public PhoneContactDTO getPhoneOther() {
    return phoneOther;
  }

  public void setPhoneOther(PhoneContactDTO phoneOther) {
    this.phoneOther = phoneOther;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PhoneUserDTO phoneUser = (PhoneUserDTO) o;
    return Objects.equals(this.id, phoneUser.id) &&
        Objects.equals(this.firstname, phoneUser.firstname) &&
        Objects.equals(this.lastname, phoneUser.lastname) &&
        Objects.equals(this.denomination, phoneUser.denomination) &&
        Objects.equals(this.company, phoneUser.company) &&
        Objects.equals(this.phonePro, phoneUser.phonePro) &&
        Objects.equals(this.phonePerso, phoneUser.phonePerso) &&
        Objects.equals(this.phoneTelavox, phoneUser.phoneTelavox) &&
        Objects.equals(this.phoneOther, phoneUser.phoneOther);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname, denomination, company, phonePro, phonePerso, phoneTelavox, phoneOther);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PhoneUserDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    firstname: ").append(toIndentedString(firstname)).append("\n");
    sb.append("    lastname: ").append(toIndentedString(lastname)).append("\n");
    sb.append("    denomination: ").append(toIndentedString(denomination)).append("\n");
    sb.append("    company: ").append(toIndentedString(company)).append("\n");
    sb.append("    phonePro: ").append(toIndentedString(phonePro)).append("\n");
    sb.append("    phonePerso: ").append(toIndentedString(phonePerso)).append("\n");
    sb.append("    phoneTelavox: ").append(toIndentedString(phoneTelavox)).append("\n");
    sb.append("    phoneOther: ").append(toIndentedString(phoneOther)).append("\n");
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

