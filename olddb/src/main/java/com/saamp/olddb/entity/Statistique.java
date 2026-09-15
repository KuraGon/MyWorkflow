package com.saamp.olddb.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;

@Entity
@Immutable // lecture seule (pratique pour une vieille base)
@Table(name = "statistiques")
public class Statistique {

    @EmbeddedId
    private StatistiqueKey id;

    @Column(name = "CA")
    private BigDecimal ca;

    @Column(name = "CA_HM")
    private BigDecimal caHm;

    @Column(name = "Marge")
    private BigDecimal marge;

    @Column(name = "Poids")
    private BigDecimal poids;

    @Column(name = "cours_fact")
    private BigDecimal coursFact;

    @Column(name = "cours_ref")
    private BigDecimal coursRef;

    public Statistique() {}

    public StatistiqueKey getId() { return id; }
    public void setId(StatistiqueKey id) { this.id = id; }

    public BigDecimal getCa() { return ca; }
    public void setCa(BigDecimal ca) { this.ca = ca; }

    public BigDecimal getCaHm() { return caHm; }
    public void setCaHm(BigDecimal caHm) { this.caHm = caHm; }

    public BigDecimal getMarge() { return marge; }
    public void setMarge(BigDecimal marge) { this.marge = marge; }

    public BigDecimal getPoids() { return poids; }
    public void setPoids(BigDecimal poids) { this.poids = poids; }

    public BigDecimal getCoursFact() { return coursFact; }
    public void setCoursFact(BigDecimal coursFact) { this.coursFact = coursFact; }

    public BigDecimal getCoursRef() { return coursRef; }
    public void setCoursRef(BigDecimal coursRef) { this.coursRef = coursRef; }
}
