package com.workflow.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.*;


import java.util.*;
import jakarta.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets TarifBaseCategory
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-07T10:14:28.616693700+01:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public enum TarifBaseCategoryDTO {
  
  BASE_OR("BASE_OR"),
  
  BASE_OR_ARGENT("BASE_OR_ARGENT"),
  
  BASE_OR_PLATINOIDE("BASE_OR_PLATINOIDE"),
  
  BASE_OR_ARGENT_PLATINOIDE("BASE_OR_ARGENT_PLATINOIDE"),
  
  BASE_ARGENT("BASE_ARGENT"),
  
  BASE_ARGENT_PLATINOIDE("BASE_ARGENT_PLATINOIDE"),
  
  BASE_PLATINOIDE("BASE_PLATINOIDE"),
  
  AUTRE("AUTRE");

  private String value;

  TarifBaseCategoryDTO(String value) {
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
  public static TarifBaseCategoryDTO fromValue(String value) {
    for (TarifBaseCategoryDTO b : TarifBaseCategoryDTO.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

