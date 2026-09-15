package com.workflow.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.workflow.dto.PriceBreakDTO;
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
 * Une grille de prix complète et éditable pour une règle
 */

@JsonTypeName("PriceGrid")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-07T10:14:28.616693700+01:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public class PriceGridDTO {

  private Long ruleId;

  /**
   * Type de règle (F ou P)
   */
  public enum RuleTypeEnum {
    FORFAIT("FORFAIT"),
    
    PRESTATION("PRESTATION");

    private String value;

    RuleTypeEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static RuleTypeEnum fromValue(String value) {
      for (RuleTypeEnum b : RuleTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private RuleTypeEnum ruleType;

  private String ruleCode;

  @Valid
  private List<@Valid PriceBreakDTO> priceBreaks = new ArrayList<>();

  public PriceGridDTO ruleId(Long ruleId) {
    this.ruleId = ruleId;
    return this;
  }

  /**
   * ID de la règle parente (F ou P)
   * @return ruleId
   */
  
  @JsonProperty("ruleId")
  public Long getRuleId() {
    return ruleId;
  }

  public void setRuleId(Long ruleId) {
    this.ruleId = ruleId;
  }

  public PriceGridDTO ruleType(RuleTypeEnum ruleType) {
    this.ruleType = ruleType;
    return this;
  }

  /**
   * Type de règle (F ou P)
   * @return ruleType
   */
  
  @JsonProperty("ruleType")
  public RuleTypeEnum getRuleType() {
    return ruleType;
  }

  public void setRuleType(RuleTypeEnum ruleType) {
    this.ruleType = ruleType;
  }

  public PriceGridDTO ruleCode(String ruleCode) {
    this.ruleCode = ruleCode;
    return this;
  }

  /**
   * Le code de liaison (ex: 'ASP1', 'FSP1', 'L301')
   * @return ruleCode
   */
  
  @JsonProperty("ruleCode")
  public String getRuleCode() {
    return ruleCode;
  }

  public void setRuleCode(String ruleCode) {
    this.ruleCode = ruleCode;
  }

  public PriceGridDTO priceBreaks(List<@Valid PriceBreakDTO> priceBreaks) {
    this.priceBreaks = priceBreaks;
    return this;
  }

  public PriceGridDTO addPriceBreaksItem(PriceBreakDTO priceBreaksItem) {
    if (this.priceBreaks == null) {
      this.priceBreaks = new ArrayList<>();
    }
    this.priceBreaks.add(priceBreaksItem);
    return this;
  }

  /**
   * Get priceBreaks
   * @return priceBreaks
   */
  @Valid 
  @JsonProperty("priceBreaks")
  public List<@Valid PriceBreakDTO> getPriceBreaks() {
    return priceBreaks;
  }

  public void setPriceBreaks(List<@Valid PriceBreakDTO> priceBreaks) {
    this.priceBreaks = priceBreaks;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PriceGridDTO priceGrid = (PriceGridDTO) o;
    return Objects.equals(this.ruleId, priceGrid.ruleId) &&
        Objects.equals(this.ruleType, priceGrid.ruleType) &&
        Objects.equals(this.ruleCode, priceGrid.ruleCode) &&
        Objects.equals(this.priceBreaks, priceGrid.priceBreaks);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ruleId, ruleType, ruleCode, priceBreaks);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PriceGridDTO {\n");
    sb.append("    ruleId: ").append(toIndentedString(ruleId)).append("\n");
    sb.append("    ruleType: ").append(toIndentedString(ruleType)).append("\n");
    sb.append("    ruleCode: ").append(toIndentedString(ruleCode)).append("\n");
    sb.append("    priceBreaks: ").append(toIndentedString(priceBreaks)).append("\n");
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

