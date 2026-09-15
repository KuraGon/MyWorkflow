package com.workflow.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.workflow.dto.TarifBaseCategoryDTO;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.*;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * TarifRuleDTO
 */

@JsonTypeName("TarifRule")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-07T10:14:28.616693700+01:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public class TarifRuleDTO {

  private Long id;

  private String natureId;

  /**
   * Gets or Sets ruleType
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

  private TarifBaseCategoryDTO baseCategory;

  private String description;

  private String codeTarifForfait;

  private String codeTarifAffinage;

  private String codeTarifAnalyse;

  private String codeTarifFonte;

  private String codeTarifPreparation;

  private Double pctAuMin;

  private Double pctAuMax;

  private Double pctAgMin;

  private Double pctAgMax;

  private Double pctPtMin;

  private Double pctPtMax;

  private Double pctPdMin;

  private Double pctPdMax;

  private Double pctRhMin;

  private Double pctRhMax;

  private Double pctIrMin;

  private Double pctIrMax;

  public TarifRuleDTO id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  
  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public TarifRuleDTO natureId(String natureId) {
    this.natureId = natureId;
    return this;
  }

  /**
   * Get natureId
   * @return natureId
   */
  
  @JsonProperty("natureId")
  public String getNatureId() {
    return natureId;
  }

  public void setNatureId(String natureId) {
    this.natureId = natureId;
  }

  public TarifRuleDTO ruleType(RuleTypeEnum ruleType) {
    this.ruleType = ruleType;
    return this;
  }

  /**
   * Get ruleType
   * @return ruleType
   */
  
  @JsonProperty("ruleType")
  public RuleTypeEnum getRuleType() {
    return ruleType;
  }

  public void setRuleType(RuleTypeEnum ruleType) {
    this.ruleType = ruleType;
  }

  public TarifRuleDTO baseCategory(TarifBaseCategoryDTO baseCategory) {
    this.baseCategory = baseCategory;
    return this;
  }

  /**
   * Get baseCategory
   * @return baseCategory
   */
  @Valid 
  @JsonProperty("baseCategory")
  public TarifBaseCategoryDTO getBaseCategory() {
    return baseCategory;
  }

  public void setBaseCategory(TarifBaseCategoryDTO baseCategory) {
    this.baseCategory = baseCategory;
  }

  public TarifRuleDTO description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
   */
  
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public TarifRuleDTO codeTarifForfait(String codeTarifForfait) {
    this.codeTarifForfait = codeTarifForfait;
    return this;
  }

  /**
   * Get codeTarifForfait
   * @return codeTarifForfait
   */
  
  @JsonProperty("codeTarifForfait")
  public String getCodeTarifForfait() {
    return codeTarifForfait;
  }

  public void setCodeTarifForfait(String codeTarifForfait) {
    this.codeTarifForfait = codeTarifForfait;
  }

  public TarifRuleDTO codeTarifAffinage(String codeTarifAffinage) {
    this.codeTarifAffinage = codeTarifAffinage;
    return this;
  }

  /**
   * Get codeTarifAffinage
   * @return codeTarifAffinage
   */
  
  @JsonProperty("codeTarifAffinage")
  public String getCodeTarifAffinage() {
    return codeTarifAffinage;
  }

  public void setCodeTarifAffinage(String codeTarifAffinage) {
    this.codeTarifAffinage = codeTarifAffinage;
  }

  public TarifRuleDTO codeTarifAnalyse(String codeTarifAnalyse) {
    this.codeTarifAnalyse = codeTarifAnalyse;
    return this;
  }

  /**
   * Get codeTarifAnalyse
   * @return codeTarifAnalyse
   */
  
  @JsonProperty("codeTarifAnalyse")
  public String getCodeTarifAnalyse() {
    return codeTarifAnalyse;
  }

  public void setCodeTarifAnalyse(String codeTarifAnalyse) {
    this.codeTarifAnalyse = codeTarifAnalyse;
  }

  public TarifRuleDTO codeTarifFonte(String codeTarifFonte) {
    this.codeTarifFonte = codeTarifFonte;
    return this;
  }

  /**
   * Get codeTarifFonte
   * @return codeTarifFonte
   */
  
  @JsonProperty("codeTarifFonte")
  public String getCodeTarifFonte() {
    return codeTarifFonte;
  }

  public void setCodeTarifFonte(String codeTarifFonte) {
    this.codeTarifFonte = codeTarifFonte;
  }

  public TarifRuleDTO codeTarifPreparation(String codeTarifPreparation) {
    this.codeTarifPreparation = codeTarifPreparation;
    return this;
  }

  /**
   * Get codeTarifPreparation
   * @return codeTarifPreparation
   */
  
  @JsonProperty("codeTarifPreparation")
  public String getCodeTarifPreparation() {
    return codeTarifPreparation;
  }

  public void setCodeTarifPreparation(String codeTarifPreparation) {
    this.codeTarifPreparation = codeTarifPreparation;
  }

  public TarifRuleDTO pctAuMin(Double pctAuMin) {
    this.pctAuMin = pctAuMin;
    return this;
  }

  /**
   * Get pctAuMin
   * @return pctAuMin
   */
  
  @JsonProperty("pctAuMin")
  public Double getPctAuMin() {
    return pctAuMin;
  }

  public void setPctAuMin(Double pctAuMin) {
    this.pctAuMin = pctAuMin;
  }

  public TarifRuleDTO pctAuMax(Double pctAuMax) {
    this.pctAuMax = pctAuMax;
    return this;
  }

  /**
   * Get pctAuMax
   * @return pctAuMax
   */
  
  @JsonProperty("pctAuMax")
  public Double getPctAuMax() {
    return pctAuMax;
  }

  public void setPctAuMax(Double pctAuMax) {
    this.pctAuMax = pctAuMax;
  }

  public TarifRuleDTO pctAgMin(Double pctAgMin) {
    this.pctAgMin = pctAgMin;
    return this;
  }

  /**
   * Get pctAgMin
   * @return pctAgMin
   */
  
  @JsonProperty("pctAgMin")
  public Double getPctAgMin() {
    return pctAgMin;
  }

  public void setPctAgMin(Double pctAgMin) {
    this.pctAgMin = pctAgMin;
  }

  public TarifRuleDTO pctAgMax(Double pctAgMax) {
    this.pctAgMax = pctAgMax;
    return this;
  }

  /**
   * Get pctAgMax
   * @return pctAgMax
   */
  
  @JsonProperty("pctAgMax")
  public Double getPctAgMax() {
    return pctAgMax;
  }

  public void setPctAgMax(Double pctAgMax) {
    this.pctAgMax = pctAgMax;
  }

  public TarifRuleDTO pctPtMin(Double pctPtMin) {
    this.pctPtMin = pctPtMin;
    return this;
  }

  /**
   * Get pctPtMin
   * @return pctPtMin
   */
  
  @JsonProperty("pctPtMin")
  public Double getPctPtMin() {
    return pctPtMin;
  }

  public void setPctPtMin(Double pctPtMin) {
    this.pctPtMin = pctPtMin;
  }

  public TarifRuleDTO pctPtMax(Double pctPtMax) {
    this.pctPtMax = pctPtMax;
    return this;
  }

  /**
   * Get pctPtMax
   * @return pctPtMax
   */
  
  @JsonProperty("pctPtMax")
  public Double getPctPtMax() {
    return pctPtMax;
  }

  public void setPctPtMax(Double pctPtMax) {
    this.pctPtMax = pctPtMax;
  }

  public TarifRuleDTO pctPdMin(Double pctPdMin) {
    this.pctPdMin = pctPdMin;
    return this;
  }

  /**
   * Get pctPdMin
   * @return pctPdMin
   */
  
  @JsonProperty("pctPdMin")
  public Double getPctPdMin() {
    return pctPdMin;
  }

  public void setPctPdMin(Double pctPdMin) {
    this.pctPdMin = pctPdMin;
  }

  public TarifRuleDTO pctPdMax(Double pctPdMax) {
    this.pctPdMax = pctPdMax;
    return this;
  }

  /**
   * Get pctPdMax
   * @return pctPdMax
   */
  
  @JsonProperty("pctPdMax")
  public Double getPctPdMax() {
    return pctPdMax;
  }

  public void setPctPdMax(Double pctPdMax) {
    this.pctPdMax = pctPdMax;
  }

  public TarifRuleDTO pctRhMin(Double pctRhMin) {
    this.pctRhMin = pctRhMin;
    return this;
  }

  /**
   * Get pctRhMin
   * @return pctRhMin
   */
  
  @JsonProperty("pctRhMin")
  public Double getPctRhMin() {
    return pctRhMin;
  }

  public void setPctRhMin(Double pctRhMin) {
    this.pctRhMin = pctRhMin;
  }

  public TarifRuleDTO pctRhMax(Double pctRhMax) {
    this.pctRhMax = pctRhMax;
    return this;
  }

  /**
   * Get pctRhMax
   * @return pctRhMax
   */
  
  @JsonProperty("pctRhMax")
  public Double getPctRhMax() {
    return pctRhMax;
  }

  public void setPctRhMax(Double pctRhMax) {
    this.pctRhMax = pctRhMax;
  }

  public TarifRuleDTO pctIrMin(Double pctIrMin) {
    this.pctIrMin = pctIrMin;
    return this;
  }

  /**
   * Get pctIrMin
   * @return pctIrMin
   */
  
  @JsonProperty("pctIrMin")
  public Double getPctIrMin() {
    return pctIrMin;
  }

  public void setPctIrMin(Double pctIrMin) {
    this.pctIrMin = pctIrMin;
  }

  public TarifRuleDTO pctIrMax(Double pctIrMax) {
    this.pctIrMax = pctIrMax;
    return this;
  }

  /**
   * Get pctIrMax
   * @return pctIrMax
   */
  
  @JsonProperty("pctIrMax")
  public Double getPctIrMax() {
    return pctIrMax;
  }

  public void setPctIrMax(Double pctIrMax) {
    this.pctIrMax = pctIrMax;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TarifRuleDTO tarifRule = (TarifRuleDTO) o;
    return Objects.equals(this.id, tarifRule.id) &&
        Objects.equals(this.natureId, tarifRule.natureId) &&
        Objects.equals(this.ruleType, tarifRule.ruleType) &&
        Objects.equals(this.baseCategory, tarifRule.baseCategory) &&
        Objects.equals(this.description, tarifRule.description) &&
        Objects.equals(this.codeTarifForfait, tarifRule.codeTarifForfait) &&
        Objects.equals(this.codeTarifAffinage, tarifRule.codeTarifAffinage) &&
        Objects.equals(this.codeTarifAnalyse, tarifRule.codeTarifAnalyse) &&
        Objects.equals(this.codeTarifFonte, tarifRule.codeTarifFonte) &&
        Objects.equals(this.codeTarifPreparation, tarifRule.codeTarifPreparation) &&
        Objects.equals(this.pctAuMin, tarifRule.pctAuMin) &&
        Objects.equals(this.pctAuMax, tarifRule.pctAuMax) &&
        Objects.equals(this.pctAgMin, tarifRule.pctAgMin) &&
        Objects.equals(this.pctAgMax, tarifRule.pctAgMax) &&
        Objects.equals(this.pctPtMin, tarifRule.pctPtMin) &&
        Objects.equals(this.pctPtMax, tarifRule.pctPtMax) &&
        Objects.equals(this.pctPdMin, tarifRule.pctPdMin) &&
        Objects.equals(this.pctPdMax, tarifRule.pctPdMax) &&
        Objects.equals(this.pctRhMin, tarifRule.pctRhMin) &&
        Objects.equals(this.pctRhMax, tarifRule.pctRhMax) &&
        Objects.equals(this.pctIrMin, tarifRule.pctIrMin) &&
        Objects.equals(this.pctIrMax, tarifRule.pctIrMax);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, natureId, ruleType, baseCategory, description, codeTarifForfait, codeTarifAffinage, codeTarifAnalyse, codeTarifFonte, codeTarifPreparation, pctAuMin, pctAuMax, pctAgMin, pctAgMax, pctPtMin, pctPtMax, pctPdMin, pctPdMax, pctRhMin, pctRhMax, pctIrMin, pctIrMax);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TarifRuleDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    natureId: ").append(toIndentedString(natureId)).append("\n");
    sb.append("    ruleType: ").append(toIndentedString(ruleType)).append("\n");
    sb.append("    baseCategory: ").append(toIndentedString(baseCategory)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    codeTarifForfait: ").append(toIndentedString(codeTarifForfait)).append("\n");
    sb.append("    codeTarifAffinage: ").append(toIndentedString(codeTarifAffinage)).append("\n");
    sb.append("    codeTarifAnalyse: ").append(toIndentedString(codeTarifAnalyse)).append("\n");
    sb.append("    codeTarifFonte: ").append(toIndentedString(codeTarifFonte)).append("\n");
    sb.append("    codeTarifPreparation: ").append(toIndentedString(codeTarifPreparation)).append("\n");
    sb.append("    pctAuMin: ").append(toIndentedString(pctAuMin)).append("\n");
    sb.append("    pctAuMax: ").append(toIndentedString(pctAuMax)).append("\n");
    sb.append("    pctAgMin: ").append(toIndentedString(pctAgMin)).append("\n");
    sb.append("    pctAgMax: ").append(toIndentedString(pctAgMax)).append("\n");
    sb.append("    pctPtMin: ").append(toIndentedString(pctPtMin)).append("\n");
    sb.append("    pctPtMax: ").append(toIndentedString(pctPtMax)).append("\n");
    sb.append("    pctPdMin: ").append(toIndentedString(pctPdMin)).append("\n");
    sb.append("    pctPdMax: ").append(toIndentedString(pctPdMax)).append("\n");
    sb.append("    pctRhMin: ").append(toIndentedString(pctRhMin)).append("\n");
    sb.append("    pctRhMax: ").append(toIndentedString(pctRhMax)).append("\n");
    sb.append("    pctIrMin: ").append(toIndentedString(pctIrMin)).append("\n");
    sb.append("    pctIrMax: ").append(toIndentedString(pctIrMax)).append("\n");
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

