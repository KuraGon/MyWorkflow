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
 * Bucket d&#39;activité par heure (0..23).
 */

@JsonTypeName("SalesRepHourBucket")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-07T10:14:28.616693700+01:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public class SalesRepHourBucketDTO {

  private Integer hour;

  private Long callCount;

  private Long smsCount;

  private Long totalCount;

  public SalesRepHourBucketDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public SalesRepHourBucketDTO(Integer hour, Long callCount, Long smsCount, Long totalCount) {
    this.hour = hour;
    this.callCount = callCount;
    this.smsCount = smsCount;
    this.totalCount = totalCount;
  }

  public SalesRepHourBucketDTO hour(Integer hour) {
    this.hour = hour;
    return this;
  }

  /**
   * Get hour
   * minimum: 0
   * maximum: 23
   * @return hour
   */
  @NotNull @Min(0) @Max(23) 
  @JsonProperty("hour")
  public Integer getHour() {
    return hour;
  }

  public void setHour(Integer hour) {
    this.hour = hour;
  }

  public SalesRepHourBucketDTO callCount(Long callCount) {
    this.callCount = callCount;
    return this;
  }

  /**
   * Nombre d'appels (hors SMS) sur l'heure
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

  public SalesRepHourBucketDTO smsCount(Long smsCount) {
    this.smsCount = smsCount;
    return this;
  }

  /**
   * Nombre de SMS sur l'heure
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

  public SalesRepHourBucketDTO totalCount(Long totalCount) {
    this.totalCount = totalCount;
    return this;
  }

  /**
   * Total interactions sur l'heure
   * @return totalCount
   */
  @NotNull 
  @JsonProperty("totalCount")
  public Long getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(Long totalCount) {
    this.totalCount = totalCount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SalesRepHourBucketDTO salesRepHourBucket = (SalesRepHourBucketDTO) o;
    return Objects.equals(this.hour, salesRepHourBucket.hour) &&
        Objects.equals(this.callCount, salesRepHourBucket.callCount) &&
        Objects.equals(this.smsCount, salesRepHourBucket.smsCount) &&
        Objects.equals(this.totalCount, salesRepHourBucket.totalCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(hour, callCount, smsCount, totalCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SalesRepHourBucketDTO {\n");
    sb.append("    hour: ").append(toIndentedString(hour)).append("\n");
    sb.append("    callCount: ").append(toIndentedString(callCount)).append("\n");
    sb.append("    smsCount: ").append(toIndentedString(smsCount)).append("\n");
    sb.append("    totalCount: ").append(toIndentedString(totalCount)).append("\n");
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

