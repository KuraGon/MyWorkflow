package com.saamp.olddb.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StatistiqueKey implements Serializable {

    @Column(name = "cl_ident")
    private Long clIdent;

    @Column(name = "agence_ident")
    private Integer agenceIdent;

    @Column(name = "commercial")
    private Integer commercial;

    @Column(name = "commercial_support")
    private Integer commercialSupport;

    @Column(name = "AV")
    private String av;

    @Column(name = "annee")
    private Integer annee;

    @Column(name = "mois")
    private Integer mois;

    @Column(name = "metal_id")
    private Integer metalId; // nullable

    @Column(name = "metier_id")
    private Integer metierId; // nullable

    public StatistiqueKey() {}

    // getters/setters

    public Long getClIdent() { return clIdent; }
    public void setClIdent(Long clIdent) { this.clIdent = clIdent; }

    public Integer getAgenceIdent() { return agenceIdent; }
    public void setAgenceIdent(Integer agenceIdent) { this.agenceIdent = agenceIdent; }

    public Integer getCommercial() { return commercial; }
    public void setCommercial(Integer commercial) { this.commercial = commercial; }

    public Integer getCommercialSupport() { return commercialSupport; }
    public void setCommercialSupport(Integer commercialSupport) { this.commercialSupport = commercialSupport; }

    public String getAv() { return av; }
    public void setAv(String av) { this.av = av; }

    public Integer getAnnee() { return annee; }
    public void setAnnee(Integer annee) { this.annee = annee; }

    public Integer getMois() { return mois; }
    public void setMois(Integer mois) { this.mois = mois; }

    public Integer getMetalId() { return metalId; }
    public void setMetalId(Integer metalId) { this.metalId = metalId; }

    public Integer getMetierId() { return metierId; }
    public void setMetierId(Integer metierId) { this.metierId = metierId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StatistiqueKey that)) return false;
        return Objects.equals(clIdent, that.clIdent)
                && Objects.equals(agenceIdent, that.agenceIdent)
                && Objects.equals(commercial, that.commercial)
                && Objects.equals(commercialSupport, that.commercialSupport)
                && Objects.equals(av, that.av)
                && Objects.equals(annee, that.annee)
                && Objects.equals(mois, that.mois)
                && Objects.equals(metalId, that.metalId)
                && Objects.equals(metierId, that.metierId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clIdent, agenceIdent, commercial, commercialSupport, av, annee, mois, metalId, metierId);
    }
}
