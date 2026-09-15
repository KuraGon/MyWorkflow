package com.saamp.proconcept.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "PCS_PC_USER") // Nom exact de la table Oracle
public class PcsPcUser {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "PC_COMP_ID")
    private Long pcCompId;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ACTIVE")
    private Integer active; // Souvent 0/1 dans les vieilles bases

    @Column(name = "FIRSTNAME")
    private String firstname;

    @Column(name = "LASTNAME")
    private String lastname;
}