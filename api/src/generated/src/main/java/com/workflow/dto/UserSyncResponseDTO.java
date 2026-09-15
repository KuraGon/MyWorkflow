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
 * UserSyncResponseDTO
 */

@JsonTypeName("UserSyncResponse")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-07T10:14:28.616693700+01:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public class UserSyncResponseDTO {

  private Boolean success;

  private String message;

  private Integer usersProcessed;

  private Integer groupsIgnored;

  public UserSyncResponseDTO success(Boolean success) {
    this.success = success;
    return this;
  }

  /**
   * Get success
   * @return success
   */
  
  @JsonProperty("success")
  public Boolean getSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  public UserSyncResponseDTO message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
   */
  
  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public UserSyncResponseDTO usersProcessed(Integer usersProcessed) {
    this.usersProcessed = usersProcessed;
    return this;
  }

  /**
   * Nombre d'utilisateurs créés ou mis à jour
   * @return usersProcessed
   */
  
  @JsonProperty("usersProcessed")
  public Integer getUsersProcessed() {
    return usersProcessed;
  }

  public void setUsersProcessed(Integer usersProcessed) {
    this.usersProcessed = usersProcessed;
  }

  public UserSyncResponseDTO groupsIgnored(Integer groupsIgnored) {
    this.groupsIgnored = groupsIgnored;
    return this;
  }

  /**
   * Nombre de lignes ignorées (car c'étaient des groupes)
   * @return groupsIgnored
   */
  
  @JsonProperty("groupsIgnored")
  public Integer getGroupsIgnored() {
    return groupsIgnored;
  }

  public void setGroupsIgnored(Integer groupsIgnored) {
    this.groupsIgnored = groupsIgnored;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserSyncResponseDTO userSyncResponse = (UserSyncResponseDTO) o;
    return Objects.equals(this.success, userSyncResponse.success) &&
        Objects.equals(this.message, userSyncResponse.message) &&
        Objects.equals(this.usersProcessed, userSyncResponse.usersProcessed) &&
        Objects.equals(this.groupsIgnored, userSyncResponse.groupsIgnored);
  }

  @Override
  public int hashCode() {
    return Objects.hash(success, message, usersProcessed, groupsIgnored);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserSyncResponseDTO {\n");
    sb.append("    success: ").append(toIndentedString(success)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    usersProcessed: ").append(toIndentedString(usersProcessed)).append("\n");
    sb.append("    groupsIgnored: ").append(toIndentedString(groupsIgnored)).append("\n");
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

