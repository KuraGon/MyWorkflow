package com.saamp.workflow.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "groupe_nature") //
public class GroupeNatureEntity {

    @Id
    @Column(name = "groupe_nature_ident")
    private String id;
    @Column(name = "groupe_nature_lib")
    private String libelle;
    @Column(name = "commentaire")
    private String commentaire;
}