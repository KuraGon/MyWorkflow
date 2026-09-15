package com.saamp.olddb.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "agence")
public class Agence {

    @Id
    @Column(name = "agence_ident")
    private Integer agenceIdent;

    @Column(name = "agence_lib")
    private String agenceLib;

    @Column(name = "adresse1")
    private String adresse1;

    @Column(name = "zip")
    private String zip;

    @Column(name = "ville")
    private String ville;

    @Column(name = "pays_code")
    private String paysCode;

    @Column(name = "tel")
    private String tel;

    @Column(name = "mail")
    private String mail;

    @Column(name = "mail2")
    private String mail2;

    @Column(name = "depot_as400")
    private String depotAs400;

    @Column(name = "agence_ordre")
    private Integer agenceOrdre;

    public Integer getAgenceIdent() { return agenceIdent; }
    public String getAgenceLib() { return agenceLib; }
    public String getAdresse1() { return adresse1; }
    public String getZip() { return zip; }
    public String getVille() { return ville; }
    public String getPaysCode() { return paysCode; }
    public String getTel() { return tel; }
    public String getMail() { return mail; }
    public String getMail2() { return mail2; }
    public String getDepotAs400() { return depotAs400; }
    public Integer getAgenceOrdre() { return agenceOrdre; }
}
