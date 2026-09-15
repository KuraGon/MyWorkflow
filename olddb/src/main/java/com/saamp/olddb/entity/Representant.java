package com.saamp.olddb.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "representant")
public class Representant {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "nom")
    private String nom;

    @Column(name = "users_id")
    private Integer usersId;

    @Column(name = "type")
    private String type;

    @Column(name = "remplace_par")
    private Integer remplacePar;

    // Sur ta capture, "agence" est une liste CSV "10,43,201"
    @Column(name = "agence")
    private String agence;

    public Integer getId() { return id; }
    public String getPrenom() { return prenom; }
    public String getNom() { return nom; }
    public Integer getUsersId() { return usersId; }
    public String getType() { return type; }
    public Integer getRemplacePar() { return remplacePar; }
    public String getAgence() { return agence; }
}
