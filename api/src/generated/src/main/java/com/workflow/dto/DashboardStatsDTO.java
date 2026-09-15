package com.workflow.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.workflow.dto.GlobalKpiDTO;
import com.workflow.dto.TopClientStatsDTO;
import com.workflow.dto.TopCommercialStatsDTO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.*;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * DashboardStatsDTO
 */

@JsonTypeName("DashboardStats")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-07T10:14:28.616693700+01:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public class DashboardStatsDTO {

  private GlobalKpiDTO kpi;

  @Valid
  private List<@Valid TopCommercialStatsDTO> topCommercials = new ArrayList<>();

  @Valid
  private List<@Valid TopClientStatsDTO> topClients = new ArrayList<>();

  public DashboardStatsDTO kpi(GlobalKpiDTO kpi) {
    this.kpi = kpi;
    return this;
  }

  /**
   * Get kpi
   * @return kpi
   */
  @Valid 
  @JsonProperty("kpi")
  public GlobalKpiDTO getKpi() {
    return kpi;
  }

  public void setKpi(GlobalKpiDTO kpi) {
    this.kpi = kpi;
  }

  public DashboardStatsDTO topCommercials(List<@Valid TopCommercialStatsDTO> topCommercials) {
    this.topCommercials = topCommercials;
    return this;
  }

  public DashboardStatsDTO addTopCommercialsItem(TopCommercialStatsDTO topCommercialsItem) {
    if (this.topCommercials == null) {
      this.topCommercials = new ArrayList<>();
    }
    this.topCommercials.add(topCommercialsItem);
    return this;
  }

  /**
   * Get topCommercials
   * @return topCommercials
   */
  @Valid 
  @JsonProperty("topCommercials")
  public List<@Valid TopCommercialStatsDTO> getTopCommercials() {
    return topCommercials;
  }

  public void setTopCommercials(List<@Valid TopCommercialStatsDTO> topCommercials) {
    this.topCommercials = topCommercials;
  }

  public DashboardStatsDTO topClients(List<@Valid TopClientStatsDTO> topClients) {
    this.topClients = topClients;
    return this;
  }

  public DashboardStatsDTO addTopClientsItem(TopClientStatsDTO topClientsItem) {
    if (this.topClients == null) {
      this.topClients = new ArrayList<>();
    }
    this.topClients.add(topClientsItem);
    return this;
  }

  /**
   * Get topClients
   * @return topClients
   */
  @Valid 
  @JsonProperty("topClients")
  public List<@Valid TopClientStatsDTO> getTopClients() {
    return topClients;
  }

  public void setTopClients(List<@Valid TopClientStatsDTO> topClients) {
    this.topClients = topClients;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DashboardStatsDTO dashboardStats = (DashboardStatsDTO) o;
    return Objects.equals(this.kpi, dashboardStats.kpi) &&
        Objects.equals(this.topCommercials, dashboardStats.topCommercials) &&
        Objects.equals(this.topClients, dashboardStats.topClients);
  }

  @Override
  public int hashCode() {
    return Objects.hash(kpi, topCommercials, topClients);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DashboardStatsDTO {\n");
    sb.append("    kpi: ").append(toIndentedString(kpi)).append("\n");
    sb.append("    topCommercials: ").append(toIndentedString(topCommercials)).append("\n");
    sb.append("    topClients: ").append(toIndentedString(topClients)).append("\n");
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

