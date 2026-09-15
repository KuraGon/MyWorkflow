package com.saamp.proconcept.repository;

import com.saamp.proconcept.model.TarifAffinagePHom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TarifAffinagePHomRepository extends JpaRepository<TarifAffinagePHom, Long> {
    List<TarifAffinagePHom> findByCodeTarifPHom(String codeTarif);

    List<TarifAffinagePHom> findByCodeTarifPHomOrderByPdsMinAsc(String ruleCode);
}
