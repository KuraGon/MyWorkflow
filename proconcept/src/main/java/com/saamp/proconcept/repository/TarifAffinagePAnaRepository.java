package com.saamp.proconcept.repository;

import com.saamp.proconcept.model.TarifAffinagePAna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TarifAffinagePAnaRepository extends JpaRepository<TarifAffinagePAna, Long> {
    List<TarifAffinagePAna> findByCodeTarifPAna(String codeTarif);

    List<TarifAffinagePAna> findByCodeTarifPAnaOrderByPdsMinAsc(String ruleCode);
}