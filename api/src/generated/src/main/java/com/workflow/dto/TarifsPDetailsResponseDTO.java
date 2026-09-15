package com.workflow.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.workflow.dto.TarifAffinagePAffDTO;
import com.workflow.dto.TarifAffinagePAnaDTO;
import com.workflow.dto.TarifAffinagePHomDTO;
import com.workflow.dto.TarifAffinagePPreDTO;
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
 * Réponse composite contenant les détails des tarifs &#39;P&#39; (affinage, analyse, etc.)
 */

@JsonTypeName("TarifsPDetailsResponse")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-07T10:14:28.616693700+01:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public class TarifsPDetailsResponseDTO {

  @Valid
  private List<@Valid TarifAffinagePAffDTO> affinage = new ArrayList<>();

  @Valid
  private List<@Valid TarifAffinagePAnaDTO> analyse = new ArrayList<>();

  @Valid
  private List<@Valid TarifAffinagePHomDTO> homogeneisation = new ArrayList<>();

  @Valid
  private List<@Valid TarifAffinagePPreDTO> preparation = new ArrayList<>();

  public TarifsPDetailsResponseDTO affinage(List<@Valid TarifAffinagePAffDTO> affinage) {
    this.affinage = affinage;
    return this;
  }

  public TarifsPDetailsResponseDTO addAffinageItem(TarifAffinagePAffDTO affinageItem) {
    if (this.affinage == null) {
      this.affinage = new ArrayList<>();
    }
    this.affinage.add(affinageItem);
    return this;
  }

  /**
   * Get affinage
   * @return affinage
   */
  @Valid 
  @JsonProperty("affinage")
  public List<@Valid TarifAffinagePAffDTO> getAffinage() {
    return affinage;
  }

  public void setAffinage(List<@Valid TarifAffinagePAffDTO> affinage) {
    this.affinage = affinage;
  }

  public TarifsPDetailsResponseDTO analyse(List<@Valid TarifAffinagePAnaDTO> analyse) {
    this.analyse = analyse;
    return this;
  }

  public TarifsPDetailsResponseDTO addAnalyseItem(TarifAffinagePAnaDTO analyseItem) {
    if (this.analyse == null) {
      this.analyse = new ArrayList<>();
    }
    this.analyse.add(analyseItem);
    return this;
  }

  /**
   * Get analyse
   * @return analyse
   */
  @Valid 
  @JsonProperty("analyse")
  public List<@Valid TarifAffinagePAnaDTO> getAnalyse() {
    return analyse;
  }

  public void setAnalyse(List<@Valid TarifAffinagePAnaDTO> analyse) {
    this.analyse = analyse;
  }

  public TarifsPDetailsResponseDTO homogeneisation(List<@Valid TarifAffinagePHomDTO> homogeneisation) {
    this.homogeneisation = homogeneisation;
    return this;
  }

  public TarifsPDetailsResponseDTO addHomogeneisationItem(TarifAffinagePHomDTO homogeneisationItem) {
    if (this.homogeneisation == null) {
      this.homogeneisation = new ArrayList<>();
    }
    this.homogeneisation.add(homogeneisationItem);
    return this;
  }

  /**
   * Get homogeneisation
   * @return homogeneisation
   */
  @Valid 
  @JsonProperty("homogeneisation")
  public List<@Valid TarifAffinagePHomDTO> getHomogeneisation() {
    return homogeneisation;
  }

  public void setHomogeneisation(List<@Valid TarifAffinagePHomDTO> homogeneisation) {
    this.homogeneisation = homogeneisation;
  }

  public TarifsPDetailsResponseDTO preparation(List<@Valid TarifAffinagePPreDTO> preparation) {
    this.preparation = preparation;
    return this;
  }

  public TarifsPDetailsResponseDTO addPreparationItem(TarifAffinagePPreDTO preparationItem) {
    if (this.preparation == null) {
      this.preparation = new ArrayList<>();
    }
    this.preparation.add(preparationItem);
    return this;
  }

  /**
   * Get preparation
   * @return preparation
   */
  @Valid 
  @JsonProperty("preparation")
  public List<@Valid TarifAffinagePPreDTO> getPreparation() {
    return preparation;
  }

  public void setPreparation(List<@Valid TarifAffinagePPreDTO> preparation) {
    this.preparation = preparation;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TarifsPDetailsResponseDTO tarifsPDetailsResponse = (TarifsPDetailsResponseDTO) o;
    return Objects.equals(this.affinage, tarifsPDetailsResponse.affinage) &&
        Objects.equals(this.analyse, tarifsPDetailsResponse.analyse) &&
        Objects.equals(this.homogeneisation, tarifsPDetailsResponse.homogeneisation) &&
        Objects.equals(this.preparation, tarifsPDetailsResponse.preparation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(affinage, analyse, homogeneisation, preparation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TarifsPDetailsResponseDTO {\n");
    sb.append("    affinage: ").append(toIndentedString(affinage)).append("\n");
    sb.append("    analyse: ").append(toIndentedString(analyse)).append("\n");
    sb.append("    homogeneisation: ").append(toIndentedString(homogeneisation)).append("\n");
    sb.append("    preparation: ").append(toIndentedString(preparation)).append("\n");
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

