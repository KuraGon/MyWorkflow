package com.saamp.proconcept.repository;

import com.saamp.proconcept.model.PcsPcUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PcsPcUserRepository extends JpaRepository<PcsPcUser, Long> {
}