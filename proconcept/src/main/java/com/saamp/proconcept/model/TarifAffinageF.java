package com.saamp.proconcept.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "IND_PTC_TARIFF_AFFINAGE_F")
public class TarifAffinageF {

    @Id
    @Column(name = "IND_PTC_TARIFF_AFFINAGE_F_ID")
    private Long id;

    @Column(name = "DIC_GCO_ATTRIBUTE_FREE_06_ID")
    private String dicGcoAttributeFree06Id;

    @Column(name = "C_IND_PTC_CODE_TARIF")
    private String codeTarif;

    @Column(name = "PCT_AU_MIN")
    private BigDecimal pctAuMin;

    @Column(name = "PCT_AU_MAX")
    private BigDecimal pctAuMax;

    @Column(name = "PCT_AG_MIN")
    private BigDecimal pctAgMin;

    @Column(name = "PCT_AG_MAX")
    private BigDecimal pctAgMax;

    @Column(name = "PCT_PT_MIN")
    private BigDecimal pctPtMin;

    @Column(name = "PCT_PT_MAX")
    private BigDecimal pctPtMax;

    @Column(name = "PCT_PD_MIN")
    private BigDecimal pctPdMin;

    @Column(name = "PCT_PD_MAX")
    private BigDecimal pctPdMax;

    @Column(name = "PCT_RH_MIN")
    private BigDecimal pctRhMin;

    @Column(name = "PCT_RH_MAX")
    private BigDecimal pctRhMax;

    @Column(name = "PCT_IR_MIN")
    private BigDecimal pctIrMin;

    @Column(name = "PCT_IR_MAX")
    private BigDecimal pctIrMax;

    @Column(name = "COMMENTAIRE")
    private String commentaire;

    @Column(name = "A_DATECRE")
    private LocalDateTime dateCreation;

    @Column(name = "A_IDCRE")
    private String idCreation;

    @Column(name = "A_DATEMOD")
    private LocalDateTime dateModification;

    @Column(name = "A_IDMOD")
    private String idModification;
}