package com.saamp.proconcept.repository;
import com.saamp.proconcept.model.TarifAffinagePAff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TarifAffinagePAffRepository extends JpaRepository<TarifAffinagePAff, Long> {
    List<TarifAffinagePAff> findByCodeTarifPAff(String codeTarif);

    List<TarifAffinagePAff> findByCodeTarifPAffOrderByPdsMinAsc(String ruleCode);
}