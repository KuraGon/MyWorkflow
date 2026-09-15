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
 * Représente un type de déchet (ex: limaille, cendres)
 */

@JsonTypeName("Nature")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-07T10:14:28.616693700+01:00[Europe/Paris]", comments = "Generator version: 7.7.0")
public class NatureDTO {

  private String id;

  private String libelle;

  private String commentaire = null;

  public NatureDTO id(String id) {
    this.id = id;
    return this;
  }

  /**
   * L'identifiant de la nature (ex: '1', '5', '11')
   * @return id
   */
  
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public NatureDTO libelle(String libelle) {
    this.libelle = libelle;
    return this;
  }

  /**
   * Le nom (ex: or fonte simple)
   * @return libelle
   */
  
  @JsonProperty("libelle")
  public String getLibelle() {
    return libelle;
  }

  public void setLibelle(String libelle) {
    this.libelle = libelle;
  }

  public NatureDTO commentaire(String commentaire) {
    this.commentaire = commentaire;
    return this;
  }

  /**
   * Exemple (ex: composants métalliques or)
   * @return commentaire
   */
  
  @JsonProperty("commentaire")
  public String getCommentaire() {
    return commentaire;
  }

  public void setCommentaire(String commentaire) {
    this.commentaire = commentaire;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NatureDTO nature = (NatureDTO) o;
    return Objects.equals(this.id, nature.id) &&
        Objects.equals(this.libelle, nature.libelle) &&
        Objects.equals(this.commentaire, nature.commentaire);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, libelle, commentaire);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NatureDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    libelle: ").append(toIndentedString(libelle)).append("\n");
    sb.append("    commentaire: ").append(toIndentedString(commentaire)).append("\n");
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

