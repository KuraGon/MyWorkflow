package com.workflow.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.workflow.dto.PhoneUserLiteDTO;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.*;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * PhoneLogDTO
 */

@JsonTypeName("PhoneLog")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-07T10:14:28.616693700+01:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public class PhoneLogDTO {

  private UUID id;

  private String forfaitMobile;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime eventDate;

  private String type;

  private String direction;

  private PhoneUserLiteDTO appelant;

  private PhoneUserLiteDTO destination;

  private String zoneClient;

  private String callType = null;

  private Integer durationSec = null;

  private Float quantityOctets = null;

  private Float costHt = null;

  public PhoneLogDTO id(UUID id) {
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

  public PhoneLogDTO forfaitMobile(String forfaitMobile) {
    this.forfaitMobile = forfaitMobile;
    return this;
  }

  /**
   * Get forfaitMobile
   * @return forfaitMobile
   */
  
  @JsonProperty("forfaitMobile")
  public String getForfaitMobile() {
    return forfaitMobile;
  }

  public void setForfaitMobile(String forfaitMobile) {
    this.forfaitMobile = forfaitMobile;
  }

  public PhoneLogDTO eventDate(OffsetDateTime eventDate) {
    this.eventDate = eventDate;
    return this;
  }

  /**
   * Get eventDate
   * @return eventDate
   */
  @Valid 
  @JsonProperty("eventDate")
  public OffsetDateTime getEventDate() {
    return eventDate;
  }

  public void setEventDate(OffsetDateTime eventDate) {
    this.eventDate = eventDate;
  }

  public PhoneLogDTO type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
   */
  
  @JsonProperty("type")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public PhoneLogDTO direction(String direction) {
    this.direction = direction;
    return this;
  }

  /**
   * Get direction
   * @return direction
   */
  
  @JsonProperty("direction")
  public String getDirection() {
    return direction;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }

  public PhoneLogDTO appelant(PhoneUserLiteDTO appelant) {
    this.appelant = appelant;
    return this;
  }

  /**
   * Get appelant
   * @return appelant
   */
  @Valid 
  @JsonProperty("appelant")
  public PhoneUserLiteDTO getAppelant() {
    return appelant;
  }

  public void setAppelant(PhoneUserLiteDTO appelant) {
    this.appelant = appelant;
  }

  public PhoneLogDTO destination(PhoneUserLiteDTO destination) {
    this.destination = destination;
    return this;
  }

  /**
   * Get destination
   * @return destination
   */
  @Valid 
  @JsonProperty("destination")
  public PhoneUserLiteDTO getDestination() {
    return destination;
  }

  public void setDestination(PhoneUserLiteDTO destination) {
    this.destination = destination;
  }

  public PhoneLogDTO zoneClient(String zoneClient) {
    this.zoneClient = zoneClient;
    return this;
  }

  /**
   * Get zoneClient
   * @return zoneClient
   */
  
  @JsonProperty("zoneClient")
  public String getZoneClient() {
    return zoneClient;
  }

  public void setZoneClient(String zoneClient) {
    this.zoneClient = zoneClient;
  }

  public PhoneLogDTO callType(String callType) {
    this.callType = callType;
    return this;
  }

  /**
   * Get callType
   * @return callType
   */
  
  @JsonProperty("callType")
  public String getCallType() {
    return callType;
  }

  public void setCallType(String callType) {
    this.callType = callType;
  }

  public PhoneLogDTO durationSec(Integer durationSec) {
    this.durationSec = durationSec;
    return this;
  }

  /**
   * Get durationSec
   * @return durationSec
   */
  
  @JsonProperty("durationSec")
  public Integer getDurationSec() {
    return durationSec;
  }

  public void setDurationSec(Integer durationSec) {
    this.durationSec = durationSec;
  }

  public PhoneLogDTO quantityOctets(Float quantityOctets) {
    this.quantityOctets = quantityOctets;
    return this;
  }

  /**
   * Get quantityOctets
   * @return quantityOctets
   */
  
  @JsonProperty("quantityOctets")
  public Float getQuantityOctets() {
    return quantityOctets;
  }

  public void setQuantityOctets(Float quantityOctets) {
    this.quantityOctets = quantityOctets;
  }

  public PhoneLogDTO costHt(Float costHt) {
    this.costHt = costHt;
    return this;
  }

  /**
   * Get costHt
   * @return costHt
   */
  
  @JsonProperty("costHt")
  public Float getCostHt() {
    return costHt;
  }

  public void setCostHt(Float costHt) {
    this.costHt = costHt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PhoneLogDTO phoneLog = (PhoneLogDTO) o;
    return Objects.equals(this.id, phoneLog.id) &&
        Objects.equals(this.forfaitMobile, phoneLog.forfaitMobile) &&
        Objects.equals(this.eventDate, phoneLog.eventDate) &&
        Objects.equals(this.type, phoneLog.type) &&
        Objects.equals(this.direction, phoneLog.direction) &&
        Objects.equals(this.appelant, phoneLog.appelant) &&
        Objects.equals(this.destination, phoneLog.destination) &&
        Objects.equals(this.zoneClient, phoneLog.zoneClient) &&
        Objects.equals(this.callType, phoneLog.callType) &&
        Objects.equals(this.durationSec, phoneLog.durationSec) &&
        Objects.equals(this.quantityOctets, phoneLog.quantityOctets) &&
        Objects.equals(this.costHt, phoneLog.costHt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, forfaitMobile, eventDate, type, direction, appelant, destination, zoneClient, callType, durationSec, quantityOctets, costHt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PhoneLogDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    forfaitMobile: ").append(toIndentedString(forfaitMobile)).append("\n");
    sb.append("    eventDate: ").append(toIndentedString(eventDate)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    direction: ").append(toIndentedString(direction)).append("\n");
    sb.append("    appelant: ").append(toIndentedString(appelant)).append("\n");
    sb.append("    destination: ").append(toIndentedString(destination)).append("\n");
    sb.append("    zoneClient: ").append(toIndentedString(zoneClient)).append("\n");
    sb.append("    callType: ").append(toIndentedString(callType)).append("\n");
    sb.append("    durationSec: ").append(toIndentedString(durationSec)).append("\n");
    sb.append("    quantityOctets: ").append(toIndentedString(quantityOctets)).append("\n");
    sb.append("    costHt: ").append(toIndentedString(costHt)).append("\n");
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

