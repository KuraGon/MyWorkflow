package com.saamp.proconcept.repository;

import com.saamp.proconcept.model.TarifAffinageF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarifAffinageFRepository extends JpaRepository<TarifAffinageF, Long> {
    List<TarifAffinageF> findByDicGcoAttributeFree06Id(String natureId);
}