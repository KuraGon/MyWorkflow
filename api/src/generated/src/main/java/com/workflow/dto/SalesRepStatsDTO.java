package com.workflow.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.workflow.dto.SalesRepHourBucketDTO;
import com.workflow.dto.SalesRepKpiDTO;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.*;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Indicateurs détaillés d&#39;un commercial (forfaitContactId) sur une période.
 */

@JsonTypeName("SalesRepStats")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-07T10:14:28.616693700+01:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public class SalesRepStatsDTO {

  private UUID commercialId;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime startDate = null;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime endDate = null;

  private SalesRepKpiDTO kpi;

  @Valid
  private List<@Valid SalesRepHourBucketDTO> activityByHour = new ArrayList<>();

  public SalesRepStatsDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public SalesRepStatsDTO(UUID commercialId, SalesRepKpiDTO kpi, List<@Valid SalesRepHourBucketDTO> activityByHour) {
    this.commercialId = commercialId;
    this.kpi = kpi;
    this.activityByHour = activityByHour;
  }

  public SalesRepStatsDTO commercialId(UUID commercialId) {
    this.commercialId = commercialId;
    return this;
  }

  /**
   * Get commercialId
   * @return commercialId
   */
  @NotNull @Valid 
  @JsonProperty("commercialId")
  public UUID getCommercialId() {
    return commercialId;
  }

  public void setCommercialId(UUID commercialId) {
    this.commercialId = commercialId;
  }

  public SalesRepStatsDTO startDate(OffsetDateTime startDate) {
    this.startDate = startDate;
    return this;
  }

  /**
   * Get startDate
   * @return startDate
   */
  @Valid 
  @JsonProperty("startDate")
  public OffsetDateTime getStartDate() {
    return startDate;
  }

  public void setStartDate(OffsetDateTime startDate) {
    this.startDate = startDate;
  }

  public SalesRepStatsDTO endDate(OffsetDateTime endDate) {
    this.endDate = endDate;
    return this;
  }

  /**
   * Get endDate
   * @return endDate
   */
  @Valid 
  @JsonProperty("endDate")
  public OffsetDateTime getEndDate() {
    return endDate;
  }

  public void setEndDate(OffsetDateTime endDate) {
    this.endDate = endDate;
  }

  public SalesRepStatsDTO kpi(SalesRepKpiDTO kpi) {
    this.kpi = kpi;
    return this;
  }

  /**
   * Get kpi
   * @return kpi
   */
  @NotNull @Valid 
  @JsonProperty("kpi")
  public SalesRepKpiDTO getKpi() {
    return kpi;
  }

  public void setKpi(SalesRepKpiDTO kpi) {
    this.kpi = kpi;
  }

  public SalesRepStatsDTO activityByHour(List<@Valid SalesRepHourBucketDTO> activityByHour) {
    this.activityByHour = activityByHour;
    return this;
  }

  public SalesRepStatsDTO addActivityByHourItem(SalesRepHourBucketDTO activityByHourItem) {
    if (this.activityByHour == null) {
      this.activityByHour = new ArrayList<>();
    }
    this.activityByHour.add(activityByHourItem);
    return this;
  }

  /**
   * Get activityByHour
   * @return activityByHour
   */
  @NotNull @Valid 
  @JsonProperty("activityByHour")
  public List<@Valid SalesRepHourBucketDTO> getActivityByHour() {
    return activityByHour;
  }

  public void setActivityByHour(List<@Valid SalesRepHourBucketDTO> activityByHour) {
    this.activityByHour = activityByHour;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SalesRepStatsDTO salesRepStats = (SalesRepStatsDTO) o;
    return Objects.equals(this.commercialId, salesRepStats.commercialId) &&
        Objects.equals(this.startDate, salesRepStats.startDate) &&
        Objects.equals(this.endDate, salesRepStats.endDate) &&
        Objects.equals(this.kpi, salesRepStats.kpi) &&
        Objects.equals(this.activityByHour, salesRepStats.activityByHour);
  }

  @Override
  public int hashCode() {
    return Objects.hash(commercialId, startDate, endDate, kpi, activityByHour);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SalesRepStatsDTO {\n");
    sb.append("    commercialId: ").append(toIndentedString(commercialId)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
    sb.append("    kpi: ").append(toIndentedString(kpi)).append("\n");
    sb.append("    activityByHour: ").append(toIndentedString(activityByHour)).append("\n");
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

