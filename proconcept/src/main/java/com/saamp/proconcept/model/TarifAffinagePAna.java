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
@Table(name = "IND_PTC_TARIFF_AFFINAGE_P_ANA")
public class TarifAffinagePAna {

    @Id
    @Column(name = "IND_PTC_TARIFF_AFFINAGE_P_ANA_ID")
    private Long id;

    @Column(name = "C_IND_PTC_CODE_TARIF_P_ANA")
    private String codeTarifPAna;

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
