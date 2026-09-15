package com.saamp.proconcept.repository;

import com.saamp.proconcept.model.TarifAffinageFDet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TarifAffinageFDetRepository extends JpaRepository<TarifAffinageFDet, Long> {
    List<TarifAffinageFDet> findByCodeTarif(String codeTarif);

    List<TarifAffinageFDet> findByCodeTarifOrderByPdsMinAsc(String ruleCode);
}