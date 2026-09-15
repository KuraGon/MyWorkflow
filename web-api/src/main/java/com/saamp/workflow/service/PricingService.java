package com.saamp.workflow.service;

import com.saamp.proconcept.model.*;
import com.saamp.proconcept.repository.*;
import com.saamp.workflow.entity.GroupeNatureEntity;
import com.saamp.workflow.mapper.PricingMapper;
import com.saamp.workflow.repository.GroupeNatureRepository;
import com.workflow.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PricingService {

    private final GroupeNatureRepository groupeNatureRepository;
    private final TarifAffinageFRepository tarifAffinageFRepository;
    private final TarifAffinagePRepository tarifAffinagePRepository;
    private final TarifAffinageFDetRepository tarifAffinageFDetRepository;
    private final TarifAffinagePAffRepository tarifAffinagePAffRepository;
    private final TarifAffinagePAnaRepository tarifAffinagePAnaRepository;
    private final TarifAffinagePHomRepository tarifAffinagePHomRepository;
    private final TarifAffinagePPreRepository tarifAffinagePPreRepository;
    private final PricingMapper pricingMapper;

    // --- LECTURE ---

    @Transactional(readOnly = true, transactionManager = "postgresTransactionManager")
    public List<NatureDTO> getAllNatures() {
        List<GroupeNatureEntity> natures = groupeNatureRepository.findAllByOrderByIdAsc();
        return pricingMapper.toNatureDtoList(natures);
    }

    @Transactional(readOnly = true, transactionManager = "oracleTransactionManager")
    public List<TarifRuleDTO> getRules(String natureId, String type) {
        if ("FORFAIT".equalsIgnoreCase(type)) {
            List<TarifAffinageF> entities = tarifAffinageFRepository.findByDicGcoAttributeFree06Id(natureId);
            return pricingMapper.toRuleDtoListFromF(entities);
        } else {
            List<TarifAffinageP> entities = tarifAffinagePRepository.findByDicGcoAttributeFree06Id(natureId);
            return pricingMapper.toRuleDtoListFromP(entities);
        }
    }

    @Transactional(readOnly = true, transactionManager = "oracleTransactionManager")
    public PriceGridDTO getPriceGrid(String ruleType, String ruleCode) {
        PriceGridDTO grid = new PriceGridDTO();
        grid.setRuleCode(ruleCode);

        if ("FORFAIT".equalsIgnoreCase(ruleType)) {
            grid.setRuleType(PriceGridDTO.RuleTypeEnum.FORFAIT);
            List<TarifAffinageFDet> details = tarifAffinageFDetRepository.findByCodeTarifOrderByPdsMinAsc(ruleCode);
            grid.setPriceBreaks(pricingMapper.toPriceBreakDtoListFromFDet(details));
        } else {
            grid.setRuleType(PriceGridDTO.RuleTypeEnum.PRESTATION);

            List<TarifAffinagePAff> affDetails = tarifAffinagePAffRepository.findByCodeTarifPAffOrderByPdsMinAsc(ruleCode);
            if (!affDetails.isEmpty()) {
                grid.setPriceBreaks(pricingMapper.toPriceBreakDtoListFromPAff(affDetails));
                return grid;
            }

            List<TarifAffinagePAna> anaDetails = tarifAffinagePAnaRepository.findByCodeTarifPAnaOrderByPdsMinAsc(ruleCode);
            if (!anaDetails.isEmpty()) {
                grid.setPriceBreaks(pricingMapper.toPriceBreakDtoListFromPAna(anaDetails));
                return grid;
            }

            List<TarifAffinagePHom> homDetails = tarifAffinagePHomRepository.findByCodeTarifPHomOrderByPdsMinAsc(ruleCode);
            if (!homDetails.isEmpty()) {
                grid.setPriceBreaks(pricingMapper.toPriceBreakDtoListFromPHom(homDetails));
                return grid;
            }

            List<TarifAffinagePPre> preDetails = tarifAffinagePPreRepository.findByCodeTarifPPreOrderByPdsMinAsc(ruleCode);
            if (!preDetails.isEmpty()) {
                grid.setPriceBreaks(pricingMapper.toPriceBreakDtoListFromPPre(preDetails));
                return grid;
            }

            grid.setPriceBreaks(new ArrayList<>());
        }
        return grid;
    }

    // --- ECRITURE ---

    @Transactional(transactionManager = "oracleTransactionManager")
    public PriceGridDTO updatePriceGrid(String ruleType, String ruleCode, PriceGridDTO inputGrid) {
        long timestampId = System.currentTimeMillis();

        if ("FORFAIT".equalsIgnoreCase(ruleType)) {
            List<TarifAffinageFDet> oldLines = tarifAffinageFDetRepository.findByCodeTarifOrderByPdsMinAsc(ruleCode);
            tarifAffinageFDetRepository.deleteAll(oldLines);
            tarifAffinageFDetRepository.flush();

            for (PriceBreakDTO pb : inputGrid.getPriceBreaks()) {
                TarifAffinageFDet entity = pricingMapper.toEntityFDet(pb);
                entity.setId(timestampId++);
                entity.setCodeTarif(ruleCode);
                entity.setCodeTarifDet(ruleCode + "-" + pb.getPdsMin().intValue());
                tarifAffinageFDetRepository.save(entity);
            }
        } else {
            // Vérification PAff
            List<TarifAffinagePAff> oldAff = tarifAffinagePAffRepository.findByCodeTarifPAffOrderByPdsMinAsc(ruleCode);
            if (!oldAff.isEmpty()) {
                tarifAffinagePAffRepository.deleteAll(oldAff);
                tarifAffinagePAffRepository.flush();
                for (PriceBreakDTO pb : inputGrid.getPriceBreaks()) {
                    TarifAffinagePAff entity = pricingMapper.toEntityPAff(pb);
                    entity.setId(timestampId++);
                    entity.setCodeTarifPAff(ruleCode);
                    tarifAffinagePAffRepository.save(entity);
                }
                return inputGrid;
            }

            // Vérification PAna
            List<TarifAffinagePAna> oldAna = tarifAffinagePAnaRepository.findByCodeTarifPAnaOrderByPdsMinAsc(ruleCode);
            if (!oldAna.isEmpty()) {
                tarifAffinagePAnaRepository.deleteAll(oldAna);
                tarifAffinagePAnaRepository.flush();
                for (PriceBreakDTO pb : inputGrid.getPriceBreaks()) {
                    TarifAffinagePAna entity = pricingMapper.toEntityPAna(pb);
                    entity.setId(timestampId++);
                    entity.setCodeTarifPAna(ruleCode);
                    tarifAffinagePAnaRepository.save(entity);
                }
                return inputGrid;
            }

            // Vérification PHom
            List<TarifAffinagePHom> oldHom = tarifAffinagePHomRepository.findByCodeTarifPHomOrderByPdsMinAsc(ruleCode);
            if (!oldHom.isEmpty()) {
                tarifAffinagePHomRepository.deleteAll(oldHom);
                tarifAffinagePHomRepository.flush();
                for (PriceBreakDTO pb : inputGrid.getPriceBreaks()) {
                    TarifAffinagePHom entity = pricingMapper.toEntityPHom(pb);
                    entity.setId(timestampId++);
                    entity.setCodeTarifPHom(ruleCode);
                    tarifAffinagePHomRepository.save(entity);
                }
                return inputGrid;
            }

            // Vérification PPre
            List<TarifAffinagePPre> oldPre = tarifAffinagePPreRepository.findByCodeTarifPPreOrderByPdsMinAsc(ruleCode);
            if (!oldPre.isEmpty()) {
                tarifAffinagePPreRepository.deleteAll(oldPre);
                tarifAffinagePPreRepository.flush();
                for (PriceBreakDTO pb : inputGrid.getPriceBreaks()) {
                    TarifAffinagePPre entity = pricingMapper.toEntityPPre(pb);
                    entity.setId(timestampId++);
                    entity.setCodeTarifPPre(ruleCode);
                    tarifAffinagePPreRepository.save(entity);
                }
                return inputGrid;
            }
        }
        return inputGrid;
    }
}