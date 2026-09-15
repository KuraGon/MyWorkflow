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
@Table(name = "IND_PTC_TARIFF_AFFINAGE_P_AFF")
public class TarifAffinagePAff {

    @Id
    @Column(name = "IND_PTC_TARIFF_AFFINAGE_P_AFF_ID")
    private Long id;

    @Column(name = "C_IND_PTC_CODE_TARIF_P_AFF")
    private String codeTarifPAff;

    @Column(name = "PAC_THIRD_ID")
    private Long pacThirdId;

    @Column(name = "PDS_MIN")
    private BigDecimal pdsMin;

    @Column(name = "PDS_MAX")
    private BigDecimal pdsMax;

    @Column(name = "PRIX_BRUT_KG")
    private BigDecimal prixBrutKg;

    @Column(name = "PRIX_BRUT_MIN")
    private BigDecimal prixBrutMin;

    @Column(name = "PCT_AU_PERTE_MIN")
    private BigDecimal pctAuPerteMin;

    @Column(name = "PDS_AU_PERTE_MIN_KG")
    private BigDecimal pdsAuPerteMinKg;

    @Column(name = "PDS_AU_PERTE_MIN_LOT")
    private BigDecimal pdsAuPerteMinLot;

    @Column(name = "PCT_AG_PERTE_MIN")
    private BigDecimal pctAgPerteMin;

    @Column(name = "PDS_AG_PERTE_MIN_KG")
    private BigDecimal pdsAgPerteMinKg;

    @Column(name = "PDS_AG_PERTE_MIN_LOT")
    private BigDecimal pdsAgPerteMinLot;

    @Column(name = "PCT_PT_PERTE_MIN")
    private BigDecimal pctPtPerteMin;

    @Column(name = "PDS_PT_PERTE_MIN_KG")
    private BigDecimal pdsPtPerteMinKg;

    @Column(name = "PDS_PT_PERTE_MIN_LOT")
    private BigDecimal pdsPtPerteMinLot;

    @Column(name = "PCT_PD_PERTE_MIN")
    private BigDecimal pctPdPerteMin;

    @Column(name = "PDS_PD_PERTE_MIN_KG")
    private BigDecimal pdsPdPerteMinKg;

    @Column(name = "PDS_PD_PERTE_MIN_LOT")
    private BigDecimal pdsPdPerteMinLot;

    @Column(name = "PCT_RH_PERTE_MIN")
    private BigDecimal pctRhPerteMin;

    @Column(name = "PDS_RH_PERTE_MIN_KG")
    private BigDecimal pdsRhPerteMinKg;

    @Column(name = "PDS_RH_PERTE_MIN_LOT")
    private BigDecimal pdsRhPerteMinLot;

    @Column(name = "PCT_IR_PERTE_MIN")
    private BigDecimal pctIrPerteMin;

    @Column(name = "PDS_IR_PERTE_MIN_KG")
    private BigDecimal pdsIrPerteMinKg;

    @Column(name = "PDS_IR_PERTE_MIN_LOT")
    private BigDecimal pdsIrPerteMinLot;

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