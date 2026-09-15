package com.saamp.proconcept.repository;

import com.saamp.proconcept.model.TarifAffinageP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarifAffinagePRepository extends JpaRepository<TarifAffinageP, Long> {
    List<TarifAffinageP> findByDicGcoAttributeFree06Id(String natureId);
}