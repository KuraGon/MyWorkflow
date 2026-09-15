package com.workflow.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.workflow.dto.PageMetaDTO;
import com.workflow.dto.PhoneUserDTO;
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
 * Page paginée de phone users
 */

@JsonTypeName("PhoneUserPage")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-07T10:14:28.616693700+01:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public class PhoneUserPageDTO {

  private PageMetaDTO meta;

  @Valid
  private List<@Valid PhoneUserDTO> items = new ArrayList<>();

  public PhoneUserPageDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public PhoneUserPageDTO(PageMetaDTO meta, List<@Valid PhoneUserDTO> items) {
    this.meta = meta;
    this.items = items;
  }

  public PhoneUserPageDTO meta(PageMetaDTO meta) {
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

  public PhoneUserPageDTO items(List<@Valid PhoneUserDTO> items) {
    this.items = items;
    return this;
  }

  public PhoneUserPageDTO addItemsItem(PhoneUserDTO itemsItem) {
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
  public List<@Valid PhoneUserDTO> getItems() {
    return items;
  }

  public void setItems(List<@Valid PhoneUserDTO> items) {
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
    PhoneUserPageDTO phoneUserPage = (PhoneUserPageDTO) o;
    return Objects.equals(this.meta, phoneUserPage.meta) &&
        Objects.equals(this.items, phoneUserPage.items);
  }

  @Override
  public int hashCode() {
    return Objects.hash(meta, items);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PhoneUserPageDTO {\n");
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

