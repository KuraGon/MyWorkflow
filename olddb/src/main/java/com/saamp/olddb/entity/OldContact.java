package com.saamp.olddb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "contact")
@Getter
@Setter
public class OldContact {

    @Id
    @Column(name = "id")
    private String id; // ou Long si ta colonne est en BIGINT

    @Column(name = "mycl_ident")
    private Integer myclIdent;

    @Column(name = "Nom")
    private String lastname;

    @Column(name = "Prenom")
    private String firstname;

    @Column(name = "MobilePhone")
    private String mobilePhone;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Title")
    private String company;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "email2", length = 100)
    private String email2;

    @Column(name = "Autre_telephone", length = 40)
    private String autreTelephone;

    @Column(name = "news_letter")
    private Boolean newsLetter; // tinyint(0/1) -> Boolean

    @Column(name = "Description", columnDefinition = "text")
    private String description;

    @Column(name = "autorisation_ident")
    private Integer autorisationIdent;

    @Column(name = "alliage_defaut_id")
    private Integer alliageDefautId;

    @Column(name = "monnaie_id")
    private Integer monnaieId;

    @Column(name = "poids_id")
    private Integer poidsId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
