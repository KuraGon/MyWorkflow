package com.saamp.proconcept.repository;

import com.saamp.proconcept.model.TarifAffinagePPre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TarifAffinagePPreRepository extends JpaRepository<TarifAffinagePPre, Long> {
    List<TarifAffinagePPre> findByCodeTarifPPre(String codeTarif);

    List<TarifAffinagePPre> findByCodeTarifPPreOrderByPdsMinAsc(String ruleCode);
}