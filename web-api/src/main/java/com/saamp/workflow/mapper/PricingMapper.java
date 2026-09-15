package com.saamp.workflow.mapper;

import com.saamp.proconcept.model.*;
import com.saamp.workflow.entity.GroupeNatureEntity;
import com.workflow.dto.NatureDTO;
import com.workflow.dto.PriceBreakDTO;
import com.workflow.dto.TarifBaseCategoryDTO;
import com.workflow.dto.TarifRuleDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PricingMapper {

    // --- NATURES (Postgres) ---
    NatureDTO toNatureDto(GroupeNatureEntity entity);
    List<NatureDTO> toNatureDtoList(List<GroupeNatureEntity> entities);

    // --- RÈGLES (Oracle Header) ---

    // Forfait (F) -> TarifRuleDTO
    @Mapping(target = "id", source = "id")
    @Mapping(target = "natureId", source = "dicGcoAttributeFree06Id")
    @Mapping(target = "ruleType", constant = "FORFAIT")
    @Mapping(target = "codeTarifForfait", source = "codeTarif")
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "baseCategory", ignore = true)
    @Mapping(target = "pctAuMin", source = "pctAuMin")
    @Mapping(target = "pctAuMax", source = "pctAuMax")
    @Mapping(target = "pctAgMin", source = "pctAgMin")
    @Mapping(target = "pctAgMax", source = "pctAgMax")
    @Mapping(target = "pctPtMin", source = "pctPtMin")
    @Mapping(target = "pctPtMax", source = "pctPtMax")
    @Mapping(target = "pctPdMin", source = "pctPdMin")
    @Mapping(target = "pctPdMax", source = "pctPdMax")
    @Mapping(target = "pctRhMin", source = "pctRhMin")
    @Mapping(target = "pctRhMax", source = "pctRhMax")
    @Mapping(target = "pctIrMin", source = "pctIrMin")
    @Mapping(target = "pctIrMax", source = "pctIrMax")
    TarifRuleDTO toRuleDtoFromF(TarifAffinageF entity);
    List<TarifRuleDTO> toRuleDtoListFromF(List<TarifAffinageF> entities);

    // PRESTATION
    @Mapping(target = "id", source = "id")
    @Mapping(target = "natureId", source = "dicGcoAttributeFree06Id")
    @Mapping(target = "ruleType", constant = "PRESTATION")
    @Mapping(target = "codeTarifAffinage", source = "codeTarifPAff")
    @Mapping(target = "codeTarifAnalyse", source = "codeTarifPAna")
    @Mapping(target = "codeTarifFonte", source = "codeTarifPHom")
    @Mapping(target = "codeTarifPreparation", source = "codeTarifPPre")
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "baseCategory", ignore = true)
    @Mapping(target = "pctAuMin", source = "pctAuMin")
    @Mapping(target = "pctAuMax", source = "pctAuMax")
    @Mapping(target = "pctAgMin", source = "pctAgMin")
    @Mapping(target = "pctAgMax", source = "pctAgMax")
    @Mapping(target = "pctPtMin", source = "pctPtMin")
    @Mapping(target = "pctPtMax", source = "pctPtMax")
    @Mapping(target = "pctPdMin", source = "pctPdMin")
    @Mapping(target = "pctPdMax", source = "pctPdMax")
    @Mapping(target = "pctRhMin", source = "pctRhMin")
    @Mapping(target = "pctRhMax", source = "pctRhMax")
    @Mapping(target = "pctIrMin", source = "pctIrMin")
    @Mapping(target = "pctIrMax", source = "pctIrMax")
    TarifRuleDTO toRuleDtoFromP(TarifAffinageP entity);
    List<TarifRuleDTO> toRuleDtoListFromP(List<TarifAffinageP> entities);

    // --- GRILLES (Oracle Details) ---

    // 1. Forfait Detail (F_DET)
    @Mapping(target = "id", source = "id")
    PriceBreakDTO toPriceBreakDto(TarifAffinageFDet entity);
    List<PriceBreakDTO> toPriceBreakDtoListFromFDet(List<TarifAffinageFDet> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pctAuPerteMin", constant = "0")
    @Mapping(target = "pctAgPerteMin", constant = "0")
    @Mapping(target = "pctPtPerteMin", constant = "0")
    @Mapping(target = "pctPdPerteMin", constant = "0")
    @Mapping(target = "pctRhPerteMin", constant = "0")
    @Mapping(target = "pctIrPerteMin", constant = "0")
    TarifAffinageFDet toEntityFDet(PriceBreakDTO dto);

    // 2. Prestation Affinage (P_AFF)
    @Mapping(target = "id", source = "id")
    PriceBreakDTO toPriceBreakDto(TarifAffinagePAff entity);
    List<PriceBreakDTO> toPriceBreakDtoListFromPAff(List<TarifAffinagePAff> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pctAuPerteMin", constant = "0")
    @Mapping(target = "pctAgPerteMin", constant = "0")
    @Mapping(target = "pctPtPerteMin", constant = "0")
    @Mapping(target = "pctPdPerteMin", constant = "0")
    @Mapping(target = "pctRhPerteMin", constant = "0")
    @Mapping(target = "pctIrPerteMin", constant = "0")
    TarifAffinagePAff toEntityPAff(PriceBreakDTO dto);

    // 3. Prestation Analyse (P_ANA)
    @Mapping(target = "id", source = "id")
    PriceBreakDTO toPriceBreakDto(TarifAffinagePAna entity);
    List<PriceBreakDTO> toPriceBreakDtoListFromPAna(List<TarifAffinagePAna> entities);

    @Mapping(target = "id", ignore = true)
    TarifAffinagePAna toEntityPAna(PriceBreakDTO dto);

    // 4. Prestation Homogénéisation/Fonte (P_HOM)
    @Mapping(target = "id", source = "id")
    PriceBreakDTO toPriceBreakDto(TarifAffinagePHom entity);
    List<PriceBreakDTO> toPriceBreakDtoListFromPHom(List<TarifAffinagePHom> entities);

    @Mapping(target = "id", ignore = true)
    TarifAffinagePHom toEntityPHom(PriceBreakDTO dto);

    // 5. Prestation Préparation (P_PRE)
    @Mapping(target = "id", source = "id")
    PriceBreakDTO toPriceBreakDto(TarifAffinagePPre entity);
    List<PriceBreakDTO> toPriceBreakDtoListFromPPre(List<TarifAffinagePPre> entities);

    @Mapping(target = "id", ignore = true)
    TarifAffinagePPre toEntityPPre(PriceBreakDTO dto);

    // --- LOGIQUE MÉTIER DE DÉCLINAISON ---

    @AfterMapping
    default void calculateMetadataF(@MappingTarget TarifRuleDTO dto, TarifAffinageF entity) {
        applyComputedFields(dto,
                entity.getPctAuMin(), entity.getPctAgMin(),
                entity.getPctPtMin(), entity.getPctPdMin(),
                entity.getPctRhMin(), entity.getPctIrMin()); // 🆕 Ajout Rh/Ir
    }

    @AfterMapping
    default void calculateMetadataP(@MappingTarget TarifRuleDTO dto, TarifAffinageP entity) {
        applyComputedFields(dto,
                entity.getPctAuMin(), entity.getPctAgMin(),
                entity.getPctPtMin(), entity.getPctPdMin(),
                entity.getPctRhMin(), entity.getPctIrMin()); // 🆕 Ajout Rh/Ir
    }

    default void applyComputedFields(TarifRuleDTO dto, BigDecimal au, BigDecimal ag, BigDecimal pt, BigDecimal pd, BigDecimal rh, BigDecimal ir) {
        // 1. Détection des présences (> 0)
        boolean hasAu = au != null && au.doubleValue() > 0;
        boolean hasAg = ag != null && ag.doubleValue() > 0;

        boolean hasPt = pt != null && pt.doubleValue() > 0;
        boolean hasPd = pd != null && pd.doubleValue() > 0;
        boolean hasRh = rh != null && rh.doubleValue() > 0;
        boolean hasIr = ir != null && ir.doubleValue() > 0;

        // PGM = Platinum Group Metals (Platine, Palladium, Rhodium, Iridium)
        boolean hasPgm = hasPt || hasPd || hasRh || hasIr;

        TarifBaseCategoryDTO category;
        String labelBase;

        // 2. Arbre de décision pour la Catégorie
        if (hasAu) {
            if (hasAg && hasPgm) {
                category = TarifBaseCategoryDTO.BASE_OR_ARGENT_PLATINOIDE;
                labelBase = "Base Or + Argent + Platinoïdes";
            } else if (hasAg) {
                category = TarifBaseCategoryDTO.BASE_OR_ARGENT;
                labelBase = "Base Or + Argent";
            } else if (hasPgm) {
                category = TarifBaseCategoryDTO.BASE_OR_PLATINOIDE;
                labelBase = "Base Or + Platinoïdes";
            } else {
                category = TarifBaseCategoryDTO.BASE_OR;
                labelBase = "Base Or";
            }
        } else if (hasAg) {
            if (hasPgm) {
                category = TarifBaseCategoryDTO.BASE_ARGENT_PLATINOIDE;
                labelBase = "Base Argent + Platinoïdes";
            } else {
                category = TarifBaseCategoryDTO.BASE_ARGENT;
                labelBase = "Base Argent";
            }
        } else if (hasPgm) {
            category = TarifBaseCategoryDTO.BASE_PLATINOIDE;
            labelBase = "Base Platinoïdes";
        } else {
            category = TarifBaseCategoryDTO.AUTRE;
            labelBase = "Autre / Déchet";
        }

        dto.setBaseCategory(category);
        dto.setDescription(labelBase);
    }

    // Conversions Types
    default Double map(BigDecimal value) {
        return value != null ? value.doubleValue() : 0.0;
    }

    default BigDecimal map(Double value) {
        return value != null ? BigDecimal.valueOf(value) : BigDecimal.ZERO;
    }


}