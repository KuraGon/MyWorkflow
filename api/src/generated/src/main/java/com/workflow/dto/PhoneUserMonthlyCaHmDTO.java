package com.workflow.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.UUID;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.*;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Lien entre un phone user MySAAMP et le cumul de CA_HM pour un mois et une année donnés. 
 */

@JsonTypeName("PhoneUserMonthlyCaHm")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-07T10:14:28.616693700+01:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public class PhoneUserMonthlyCaHmDTO {

  private UUID phoneUserId;

  private Long mysaampIdClient;

  private Integer annee;

  private Integer mois;

  private Double caHm;

  private String label;

  public PhoneUserMonthlyCaHmDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public PhoneUserMonthlyCaHmDTO(UUID phoneUserId, Long mysaampIdClient, Integer annee, Integer mois, Double caHm) {
    this.phoneUserId = phoneUserId;
    this.mysaampIdClient = mysaampIdClient;
    this.annee = annee;
    this.mois = mois;
    this.caHm = caHm;
  }

  public PhoneUserMonthlyCaHmDTO phoneUserId(UUID phoneUserId) {
    this.phoneUserId = phoneUserId;
    return this;
  }

  /**
   * Identifiant interne du phone user
   * @return phoneUserId
   */
  @NotNull @Valid 
  @JsonProperty("phoneUserId")
  public UUID getPhoneUserId() {
    return phoneUserId;
  }

  public void setPhoneUserId(UUID phoneUserId) {
    this.phoneUserId = phoneUserId;
  }

  public PhoneUserMonthlyCaHmDTO mysaampIdClient(Long mysaampIdClient) {
    this.mysaampIdClient = mysaampIdClient;
    return this;
  }

  /**
   * Identifiant client MySAAMP (équivalent cl_ident en old DB)
   * @return mysaampIdClient
   */
  @NotNull 
  @JsonProperty("mysaampIdClient")
  public Long getMysaampIdClient() {
    return mysaampIdClient;
  }

  public void setMysaampIdClient(Long mysaampIdClient) {
    this.mysaampIdClient = mysaampIdClient;
  }

  public PhoneUserMonthlyCaHmDTO annee(Integer annee) {
    this.annee = annee;
    return this;
  }

  /**
   * Année de la statistique
   * @return annee
   */
  @NotNull 
  @JsonProperty("annee")
  public Integer getAnnee() {
    return annee;
  }

  public void setAnnee(Integer annee) {
    this.annee = annee;
  }

  public PhoneUserMonthlyCaHmDTO mois(Integer mois) {
    this.mois = mois;
    return this;
  }

  /**
   * Mois de la statistique
   * minimum: 1
   * maximum: 12
   * @return mois
   */
  @NotNull @Min(1) @Max(12) 
  @JsonProperty("mois")
  public Integer getMois() {
    return mois;
  }

  public void setMois(Integer mois) {
    this.mois = mois;
  }

  public PhoneUserMonthlyCaHmDTO caHm(Double caHm) {
    this.caHm = caHm;
    return this;
  }

  /**
   * Cumul de CA_HM sur le mois
   * @return caHm
   */
  @NotNull 
  @JsonProperty("caHm")
  public Double getCaHm() {
    return caHm;
  }

  public void setCaHm(Double caHm) {
    this.caHm = caHm;
  }

  public PhoneUserMonthlyCaHmDTO label(String label) {
    this.label = label;
    return this;
  }

  /**
   * Libellé lisible du phone user / client
   * @return label
   */
  
  @JsonProperty("label")
  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PhoneUserMonthlyCaHmDTO phoneUserMonthlyCaHm = (PhoneUserMonthlyCaHmDTO) o;
    return Objects.equals(this.phoneUserId, phoneUserMonthlyCaHm.phoneUserId) &&
        Objects.equals(this.mysaampIdClient, phoneUserMonthlyCaHm.mysaampIdClient) &&
        Objects.equals(this.annee, phoneUserMonthlyCaHm.annee) &&
        Objects.equals(this.mois, phoneUserMonthlyCaHm.mois) &&
        Objects.equals(this.caHm, phoneUserMonthlyCaHm.caHm) &&
        Objects.equals(this.label, phoneUserMonthlyCaHm.label);
  }

  @Override
  public int hashCode() {
    return Objects.hash(phoneUserId, mysaampIdClient, annee, mois, caHm, label);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PhoneUserMonthlyCaHmDTO {\n");
    sb.append("    phoneUserId: ").append(toIndentedString(phoneUserId)).append("\n");
    sb.append("    mysaampIdClient: ").append(toIndentedString(mysaampIdClient)).append("\n");
    sb.append("    annee: ").append(toIndentedString(annee)).append("\n");
    sb.append("    mois: ").append(toIndentedString(mois)).append("\n");
    sb.append("    caHm: ").append(toIndentedString(caHm)).append("\n");
    sb.append("    label: ").append(toIndentedString(label)).append("\n");
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

