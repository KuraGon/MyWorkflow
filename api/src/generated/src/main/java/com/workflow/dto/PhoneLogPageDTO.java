package com.workflow.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.workflow.dto.PageMetaDTO;
import com.workflow.dto.PhoneLogDTO;
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
 * PhoneLogPageDTO
 */

@JsonTypeName("PhoneLogPage")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-07T10:14:28.616693700+01:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public class PhoneLogPageDTO {

  private PageMetaDTO meta;

  @Valid
  private List<@Valid PhoneLogDTO> items = new ArrayList<>();

  public PhoneLogPageDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public PhoneLogPageDTO(PageMetaDTO meta, List<@Valid PhoneLogDTO> items) {
    this.meta = meta;
    this.items = items;
  }

  public PhoneLogPageDTO meta(PageMetaDTO meta) {
    this.meta = meta;
    return this;
  }

  /**
   * Get meta
   * @return meta
   */
  @NotNull @Valid 
  @JsonProperty("meta")
  public PageMetaDTO getMeta() {
    return meta;
  }

  public void setMeta(PageMetaDTO meta) {
    this.meta = meta;
  }

  public PhoneLogPageDTO items(List<@Valid PhoneLogDTO> items) {
    this.items = items;
    return this;
  }

  public PhoneLogPageDTO addItemsItem(PhoneLogDTO itemsItem) {
    if (this.items == null) {
      this.items = new ArrayList<>();
    }
    this.items.add(itemsItem);
    return this;
  }

  /**
   * Get items
   * @return items
   */
  @NotNull @Valid 
  @JsonProperty("items")
  public List<@Valid PhoneLogDTO> getItems() {
    return items;
  }

  public void setItems(List<@Valid PhoneLogDTO> items) {
    this.items = items;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PhoneLogPageDTO phoneLogPage = (PhoneLogPageDTO) o;
    return Objects.equals(this.meta, phoneLogPage.meta) &&
        Objects.equals(this.items, phoneLogPage.items);
  }

  @Override
  public int hashCode() {
    return Objects.hash(meta, items);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PhoneLogPageDTO {\n");
    sb.append("    meta: ").append(toIndentedString(meta)).append("\n");
    sb.append("    items: ").append(toIndentedString(items)).append("\n");
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

