package com.saamp.workflow.mapper;

import com.saamp.proconcept.model.TarifAffinageF;
import com.saamp.proconcept.model.TarifAffinageFDet;
import com.saamp.proconcept.model.TarifAffinageP;
import com.saamp.proconcept.model.TarifAffinagePAff;
import com.saamp.proconcept.model.TarifAffinagePAna;
import com.saamp.proconcept.model.TarifAffinagePHom;
import com.saamp.proconcept.model.TarifAffinagePPre;
import com.saamp.workflow.entity.GroupeNatureEntity;
import com.workflow.dto.NatureDTO;
import com.workflow.dto.PriceBreakDTO;
import com.workflow.dto.TarifRuleDTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-18T09:28:32+0100",
    comments = "version: 1.6.2, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.9 (Amazon.com Inc.)"
)
@Component
public class PricingMapperImpl implements PricingMapper {

    @Override
    public NatureDTO toNatureDto(GroupeNatureEntity entity) {
        if ( entity == null ) {
            return null;
        }

        NatureDTO natureDTO = new NatureDTO();

        natureDTO.setId( entity.getId() );
        natureDTO.setLibelle( entity.getLibelle() );
        natureDTO.setCommentaire( entity.getCommentaire() );

        return natureDTO;
    }

    @Override
    public List<NatureDTO> toNatureDtoList(List<GroupeNatureEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<NatureDTO> list = new ArrayList<NatureDTO>( entities.size() );
        for ( GroupeNatureEntity groupeNatureEntity : entities ) {
            list.add( toNatureDto( groupeNatureEntity ) );
        }

        return list;
    }

    @Override
    public TarifRuleDTO toRuleDtoFromF(TarifAffinageF entity) {
        if ( entity == null ) {
            return null;
        }

        TarifRuleDTO tarifRuleDTO = new TarifRuleDTO();

        tarifRuleDTO.setId( entity.getId() );
        tarifRuleDTO.setNatureId( entity.getDicGcoAttributeFree06Id() );
        tarifRuleDTO.setCodeTarifForfait( entity.getCodeTarif() );
        tarifRuleDTO.setPctAuMin( map( entity.getPctAuMin() ) );
        tarifRuleDTO.setPctAuMax( map( entity.getPctAuMax() ) );
        tarifRuleDTO.setPctAgMin( map( entity.getPctAgMin() ) );
        tarifRuleDTO.setPctAgMax( map( entity.getPctAgMax() ) );
        tarifRuleDTO.setPctPtMin( map( entity.getPctPtMin() ) );
        tarifRuleDTO.setPctPtMax( map( entity.getPctPtMax() ) );
        tarifRuleDTO.setPctPdMin( map( entity.getPctPdMin() ) );
        tarifRuleDTO.setPctPdMax( map( entity.getPctPdMax() ) );
        tarifRuleDTO.setPctRhMin( map( entity.getPctRhMin() ) );
        tarifRuleDTO.setPctRhMax( map( entity.getPctRhMax() ) );
        tarifRuleDTO.setPctIrMin( map( entity.getPctIrMin() ) );
        tarifRuleDTO.setPctIrMax( map( entity.getPctIrMax() ) );

        tarifRuleDTO.setRuleType( TarifRuleDTO.RuleTypeEnum.FORFAIT );

        calculateMetadataF( tarifRuleDTO, entity );

        return tarifRuleDTO;
    }

    @Override
    public List<TarifRuleDTO> toRuleDtoListFromF(List<TarifAffinageF> entities) {
        if ( entities == null ) {
            return null;
        }

        List<TarifRuleDTO> list = new ArrayList<TarifRuleDTO>( entities.size() );
        for ( TarifAffinageF tarifAffinageF : entities ) {
            list.add( toRuleDtoFromF( tarifAffinageF ) );
        }

        return list;
    }

    @Override
    public TarifRuleDTO toRuleDtoFromP(TarifAffinageP entity) {
        if ( entity == null ) {
            return null;
        }

        TarifRuleDTO tarifRuleDTO = new TarifRuleDTO();

        tarifRuleDTO.setId( entity.getId() );
        tarifRuleDTO.setNatureId( entity.getDicGcoAttributeFree06Id() );
        tarifRuleDTO.setCodeTarifAffinage( entity.getCodeTarifPAff() );
        tarifRuleDTO.setCodeTarifAnalyse( entity.getCodeTarifPAna() );
        tarifRuleDTO.setCodeTarifFonte( entity.getCodeTarifPHom() );
        tarifRuleDTO.setCodeTarifPreparation( entity.getCodeTarifPPre() );
        tarifRuleDTO.setPctAuMin( map( entity.getPctAuMin() ) );
        tarifRuleDTO.setPctAuMax( map( entity.getPctAuMax() ) );
        tarifRuleDTO.setPctAgMin( map( entity.getPctAgMin() ) );
        tarifRuleDTO.setPctAgMax( map( entity.getPctAgMax() ) );
        tarifRuleDTO.setPctPtMin( map( entity.getPctPtMin() ) );
        tarifRuleDTO.setPctPtMax( map( entity.getPctPtMax() ) );
        tarifRuleDTO.setPctPdMin( map( entity.getPctPdMin() ) );
        tarifRuleDTO.setPctPdMax( map( entity.getPctPdMax() ) );
        tarifRuleDTO.setPctRhMin( map( entity.getPctRhMin() ) );
        tarifRuleDTO.setPctRhMax( map( entity.getPctRhMax() ) );
        tarifRuleDTO.setPctIrMin( map( entity.getPctIrMin() ) );
        tarifRuleDTO.setPctIrMax( map( entity.getPctIrMax() ) );

        tarifRuleDTO.setRuleType( TarifRuleDTO.RuleTypeEnum.PRESTATION );

        calculateMetadataP( tarifRuleDTO, entity );

        return tarifRuleDTO;
    }

    @Override
    public List<TarifRuleDTO> toRuleDtoListFromP(List<TarifAffinageP> entities) {
        if ( entities == null ) {
            return null;
        }

        List<TarifRuleDTO> list = new ArrayList<TarifRuleDTO>( entities.size() );
        for ( TarifAffinageP tarifAffinageP : entities ) {
            list.add( toRuleDtoFromP( tarifAffinageP ) );
        }

        return list;
    }

    @Override
    public PriceBreakDTO toPriceBreakDto(TarifAffinageFDet entity) {
        if ( entity == null ) {
            return null;
        }

        PriceBreakDTO priceBreakDTO = new PriceBreakDTO();

        priceBreakDTO.setId( entity.getId() );
        priceBreakDTO.setPdsMin( entity.getPdsMin() );
        priceBreakDTO.setPdsMax( entity.getPdsMax() );
        priceBreakDTO.setPrixBrutKg( entity.getPrixBrutKg() );
        priceBreakDTO.setPrixBrutMin( entity.getPrixBrutMin() );
        priceBreakDTO.setPctAuPerteMin( entity.getPctAuPerteMin() );
        priceBreakDTO.setPdsAuPerteMinKg( entity.getPdsAuPerteMinKg() );
        priceBreakDTO.setPdsAuPerteMinLot( entity.getPdsAuPerteMinLot() );
        priceBreakDTO.setPctAuDecote( entity.getPctAuDecote() );
        priceBreakDTO.setPctAgPerteMin( entity.getPctAgPerteMin() );
        priceBreakDTO.setPdsAgPerteMinKg( entity.getPdsAgPerteMinKg() );
        priceBreakDTO.setPdsAgPerteMinLot( entity.getPdsAgPerteMinLot() );
        priceBreakDTO.setPctAgDecote( entity.getPctAgDecote() );
        priceBreakDTO.setPctPtPerteMin( entity.getPctPtPerteMin() );
        priceBreakDTO.setPdsPtPerteMinKg( entity.getPdsPtPerteMinKg() );
        priceBreakDTO.setPdsPtPerteMinLot( entity.getPdsPtPerteMinLot() );
        priceBreakDTO.setPctPtDecote( entity.getPctPtDecote() );
        priceBreakDTO.setPctPdPerteMin( entity.getPctPdPerteMin() );
        priceBreakDTO.setPdsPdPerteMinKg( entity.getPdsPdPerteMinKg() );
        priceBreakDTO.setPdsPdPerteMinLot( entity.getPdsPdPerteMinLot() );
        priceBreakDTO.setPctPdDecote( entity.getPctPdDecote() );
        priceBreakDTO.setPctRhPerteMin( entity.getPctRhPerteMin() );
        priceBreakDTO.setPdsRhPerteMinKg( entity.getPdsRhPerteMinKg() );
        priceBreakDTO.setPdsRhPerteMinLot( entity.getPdsRhPerteMinLot() );
        priceBreakDTO.setPctRhDecote( entity.getPctRhDecote() );
        priceBreakDTO.setPctIrPerteMin( entity.getPctIrPerteMin() );
        priceBreakDTO.setPdsIrPerteMinKg( entity.getPdsIrPerteMinKg() );
        priceBreakDTO.setPdsIrPerteMinLot( entity.getPdsIrPerteMinLot() );
        priceBreakDTO.setPctIrDecote( entity.getPctIrDecote() );

        return priceBreakDTO;
    }

    @Override
    public List<PriceBreakDTO> toPriceBreakDtoListFromFDet(List<TarifAffinageFDet> entities) {
        if ( entities == null ) {
            return null;
        }

        List<PriceBreakDTO> list = new ArrayList<PriceBreakDTO>( entities.size() );
        for ( TarifAffinageFDet tarifAffinageFDet : entities ) {
            list.add( toPriceBreakDto( tarifAffinageFDet ) );
        }

        return list;
    }

    @Override
    public TarifAffinageFDet toEntityFDet(PriceBreakDTO dto) {
        if ( dto == null ) {
            return null;
        }

        TarifAffinageFDet tarifAffinageFDet = new TarifAffinageFDet();

        tarifAffinageFDet.setPdsMin( dto.getPdsMin() );
        tarifAffinageFDet.setPdsMax( dto.getPdsMax() );
        tarifAffinageFDet.setPrixBrutKg( dto.getPrixBrutKg() );
        tarifAffinageFDet.setPrixBrutMin( dto.getPrixBrutMin() );
        tarifAffinageFDet.setPdsAuPerteMinKg( dto.getPdsAuPerteMinKg() );
        tarifAffinageFDet.setPdsAuPerteMinLot( dto.getPdsAuPerteMinLot() );
        tarifAffinageFDet.setPctAuDecote( dto.getPctAuDecote() );
        tarifAffinageFDet.setPdsAgPerteMinKg( dto.getPdsAgPerteMinKg() );
        tarifAffinageFDet.setPdsAgPerteMinLot( dto.getPdsAgPerteMinLot() );
        tarifAffinageFDet.setPctAgDecote( dto.getPctAgDecote() );
        tarifAffinageFDet.setPdsPtPerteMinKg( dto.getPdsPtPerteMinKg() );
        tarifAffinageFDet.setPdsPtPerteMinLot( dto.getPdsPtPerteMinLot() );
        tarifAffinageFDet.setPctPtDecote( dto.getPctPtDecote() );
        tarifAffinageFDet.setPdsPdPerteMinKg( dto.getPdsPdPerteMinKg() );
        tarifAffinageFDet.setPdsPdPerteMinLot( dto.getPdsPdPerteMinLot() );
        tarifAffinageFDet.setPctPdDecote( dto.getPctPdDecote() );
        tarifAffinageFDet.setPdsRhPerteMinKg( dto.getPdsRhPerteMinKg() );
        tarifAffinageFDet.setPdsRhPerteMinLot( dto.getPdsRhPerteMinLot() );
        tarifAffinageFDet.setPctRhDecote( dto.getPctRhDecote() );
        tarifAffinageFDet.setPdsIrPerteMinKg( dto.getPdsIrPerteMinKg() );
        tarifAffinageFDet.setPdsIrPerteMinLot( dto.getPdsIrPerteMinLot() );
        tarifAffinageFDet.setPctIrDecote( dto.getPctIrDecote() );

        tarifAffinageFDet.setPctAuPerteMin( new BigDecimal( "0" ) );
        tarifAffinageFDet.setPctAgPerteMin( new BigDecimal( "0" ) );
        tarifAffinageFDet.setPctPtPerteMin( new BigDecimal( "0" ) );
        tarifAffinageFDet.setPctPdPerteMin( new BigDecimal( "0" ) );
        tarifAffinageFDet.setPctRhPerteMin( new BigDecimal( "0" ) );
        tarifAffinageFDet.setPctIrPerteMin( new BigDecimal( "0" ) );

        return tarifAffinageFDet;
    }

    @Override
    public PriceBreakDTO toPriceBreakDto(TarifAffinagePAff entity) {
        if ( entity == null ) {
            return null;
        }

        PriceBreakDTO priceBreakDTO = new PriceBreakDTO();

        priceBreakDTO.setId( entity.getId() );
        priceBreakDTO.setPdsMin( entity.getPdsMin() );
        priceBreakDTO.setPdsMax( entity.getPdsMax() );
        priceBreakDTO.setPrixBrutKg( entity.getPrixBrutKg() );
        priceBreakDTO.setPrixBrutMin( entity.getPrixBrutMin() );
        priceBreakDTO.setPctAuPerteMin( entity.getPctAuPerteMin() );
        priceBreakDTO.setPdsAuPerteMinKg( entity.getPdsAuPerteMinKg() );
        priceBreakDTO.setPdsAuPerteMinLot( entity.getPdsAuPerteMinLot() );
        priceBreakDTO.setPctAgPerteMin( entity.getPctAgPerteMin() );
        priceBreakDTO.setPdsAgPerteMinKg( entity.getPdsAgPerteMinKg() );
        priceBreakDTO.setPdsAgPerteMinLot( entity.getPdsAgPerteMinLot() );
        priceBreakDTO.setPctPtPerteMin( entity.getPctPtPerteMin() );
        priceBreakDTO.setPdsPtPerteMinKg( entity.getPdsPtPerteMinKg() );
        priceBreakDTO.setPdsPtPerteMinLot( entity.getPdsPtPerteMinLot() );
        priceBreakDTO.setPctPdPerteMin( entity.getPctPdPerteMin() );
        priceBreakDTO.setPdsPdPerteMinKg( entity.getPdsPdPerteMinKg() );
        priceBreakDTO.setPdsPdPerteMinLot( entity.getPdsPdPerteMinLot() );
        priceBreakDTO.setPctRhPerteMin( entity.getPctRhPerteMin() );
        priceBreakDTO.setPdsRhPerteMinKg( entity.getPdsRhPerteMinKg() );
        priceBreakDTO.setPdsRhPerteMinLot( entity.getPdsRhPerteMinLot() );
        priceBreakDTO.setPctIrPerteMin( entity.getPctIrPerteMin() );
        priceBreakDTO.setPdsIrPerteMinKg( entity.getPdsIrPerteMinKg() );
        priceBreakDTO.setPdsIrPerteMinLot( entity.getPdsIrPerteMinLot() );

        return priceBreakDTO;
    }

    @Override
    public List<PriceBreakDTO> toPriceBreakDtoListFromPAff(List<TarifAffinagePAff> entities) {
        if ( entities == null ) {
            return null;
        }

        List<PriceBreakDTO> list = new ArrayList<PriceBreakDTO>( entities.size() );
        for ( TarifAffinagePAff tarifAffinagePAff : entities ) {
            list.add( toPriceBreakDto( tarifAffinagePAff ) );
        }

        return list;
    }

    @Override
    public TarifAffinagePAff toEntityPAff(PriceBreakDTO dto) {
        if ( dto == null ) {
            return null;
        }

        TarifAffinagePAff tarifAffinagePAff = new TarifAffinagePAff();

        tarifAffinagePAff.setPdsMin( dto.getPdsMin() );
        tarifAffinagePAff.setPdsMax( dto.getPdsMax() );
        tarifAffinagePAff.setPrixBrutKg( dto.getPrixBrutKg() );
        tarifAffinagePAff.setPrixBrutMin( dto.getPrixBrutMin() );
        tarifAffinagePAff.setPdsAuPerteMinKg( dto.getPdsAuPerteMinKg() );
        tarifAffinagePAff.setPdsAuPerteMinLot( dto.getPdsAuPerteMinLot() );
        tarifAffinagePAff.setPdsAgPerteMinKg( dto.getPdsAgPerteMinKg() );
        tarifAffinagePAff.setPdsAgPerteMinLot( dto.getPdsAgPerteMinLot() );
        tarifAffinagePAff.setPdsPtPerteMinKg( dto.getPdsPtPerteMinKg() );
        tarifAffinagePAff.setPdsPtPerteMinLot( dto.getPdsPtPerteMinLot() );
        tarifAffinagePAff.setPdsPdPerteMinKg( dto.getPdsPdPerteMinKg() );
        tarifAffinagePAff.setPdsPdPerteMinLot( dto.getPdsPdPerteMinLot() );
        tarifAffinagePAff.setPdsRhPerteMinKg( dto.getPdsRhPerteMinKg() );
        tarifAffinagePAff.setPdsRhPerteMinLot( dto.getPdsRhPerteMinLot() );
        tarifAffinagePAff.setPdsIrPerteMinKg( dto.getPdsIrPerteMinKg() );
        tarifAffinagePAff.setPdsIrPerteMinLot( dto.getPdsIrPerteMinLot() );

        tarifAffinagePAff.setPctAuPerteMin( new BigDecimal( "0" ) );
        tarifAffinagePAff.setPctAgPerteMin( new BigDecimal( "0" ) );
        tarifAffinagePAff.setPctPtPerteMin( new BigDecimal( "0" ) );
        tarifAffinagePAff.setPctPdPerteMin( new BigDecimal( "0" ) );
        tarifAffinagePAff.setPctRhPerteMin( new BigDecimal( "0" ) );
        tarifAffinagePAff.setPctIrPerteMin( new BigDecimal( "0" ) );

        return tarifAffinagePAff;
    }

    @Override
    public PriceBreakDTO toPriceBreakDto(TarifAffinagePAna entity) {
        if ( entity == null ) {
            return null;
        }

        PriceBreakDTO priceBreakDTO = new PriceBreakDTO();

        priceBreakDTO.setId( entity.getId() );
        priceBreakDTO.setPdsMin( entity.getPdsMin() );
        priceBreakDTO.setPdsMax( entity.getPdsMax() );
        priceBreakDTO.setPrixBrutKg( entity.getPrixBrutKg() );
        priceBreakDTO.setPrixBrutMin( entity.getPrixBrutMin() );

        return priceBreakDTO;
    }

    @Override
    public List<PriceBreakDTO> toPriceBreakDtoListFromPAna(List<TarifAffinagePAna> entities) {
        if ( entities == null ) {
            return null;
        }

        List<PriceBreakDTO> list = new ArrayList<PriceBreakDTO>( entities.size() );
        for ( TarifAffinagePAna tarifAffinagePAna : entities ) {
            list.add( toPriceBreakDto( tarifAffinagePAna ) );
        }

        return list;
    }

    @Override
    public TarifAffinagePAna toEntityPAna(PriceBreakDTO dto) {
        if ( dto == null ) {
            return null;
        }

        TarifAffinagePAna tarifAffinagePAna = new TarifAffinagePAna();

        tarifAffinagePAna.setPdsMin( dto.getPdsMin() );
        tarifAffinagePAna.setPdsMax( dto.getPdsMax() );
        tarifAffinagePAna.setPrixBrutKg( dto.getPrixBrutKg() );
        tarifAffinagePAna.setPrixBrutMin( dto.getPrixBrutMin() );

        return tarifAffinagePAna;
    }

    @Override
    public PriceBreakDTO toPriceBreakDto(TarifAffinagePHom entity) {
        if ( entity == null ) {
            return null;
        }

        PriceBreakDTO priceBreakDTO = new PriceBreakDTO();

        priceBreakDTO.setId( entity.getId() );
        priceBreakDTO.setPdsMin( entity.getPdsMin() );
        priceBreakDTO.setPdsMax( entity.getPdsMax() );
        priceBreakDTO.setPrixBrutKg( entity.getPrixBrutKg() );
        priceBreakDTO.setPrixBrutMin( entity.getPrixBrutMin() );

        return priceBreakDTO;
    }

    @Override
    public List<PriceBreakDTO> toPriceBreakDtoListFromPHom(List<TarifAffinagePHom> entities) {
        if ( entities == null ) {
            return null;
        }

        List<PriceBreakDTO> list = new ArrayList<PriceBreakDTO>( entities.size() );
        for ( TarifAffinagePHom tarifAffinagePHom : entities ) {
            list.add( toPriceBreakDto( tarifAffinagePHom ) );
        }

        return list;
    }

    @Override
    public TarifAffinagePHom toEntityPHom(PriceBreakDTO dto) {
        if ( dto == null ) {
            return null;
        }

        TarifAffinagePHom tarifAffinagePHom = new TarifAffinagePHom();

        tarifAffinagePHom.setPdsMin( dto.getPdsMin() );
        tarifAffinagePHom.setPdsMax( dto.getPdsMax() );
        tarifAffinagePHom.setPrixBrutKg( dto.getPrixBrutKg() );
        tarifAffinagePHom.setPrixBrutMin( dto.getPrixBrutMin() );

        return tarifAffinagePHom;
    }

    @Override
    public PriceBreakDTO toPriceBreakDto(TarifAffinagePPre entity) {
        if ( entity == null ) {
            return null;
        }

        PriceBreakDTO priceBreakDTO = new PriceBreakDTO();

        priceBreakDTO.setId( entity.getId() );
        priceBreakDTO.setPdsMin( entity.getPdsMin() );
        priceBreakDTO.setPdsMax( entity.getPdsMax() );
        priceBreakDTO.setPrixBrutKg( entity.getPrixBrutKg() );
        priceBreakDTO.setPrixBrutMin( entity.getPrixBrutMin() );

        return priceBreakDTO;
    }

    @Override
    public List<PriceBreakDTO> toPriceBreakDtoListFromPPre(List<TarifAffinagePPre> entities) {
        if ( entities == null ) {
            return null;
        }

        List<PriceBreakDTO> list = new ArrayList<PriceBreakDTO>( entities.size() );
        for ( TarifAffinagePPre tarifAffinagePPre : entities ) {
            list.add( toPriceBreakDto( tarifAffinagePPre ) );
        }

        return list;
    }

    @Override
    public TarifAffinagePPre toEntityPPre(PriceBreakDTO dto) {
        if ( dto == null ) {
            return null;
        }

        TarifAffinagePPre tarifAffinagePPre = new TarifAffinagePPre();

        tarifAffinagePPre.setPdsMin( dto.getPdsMin() );
        tarifAffinagePPre.setPdsMax( dto.getPdsMax() );
        tarifAffinagePPre.setPrixBrutKg( dto.getPrixBrutKg() );
        tarifAffinagePPre.setPrixBrutMin( dto.getPrixBrutMin() );

        return tarifAffinagePPre;
    }
}
