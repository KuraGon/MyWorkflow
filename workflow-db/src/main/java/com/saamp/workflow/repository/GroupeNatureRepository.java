package com.saamp.workflow.repository;

import com.saamp.workflow.entity.GroupeNatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupeNatureRepository extends JpaRepository<GroupeNatureEntity, String> {
    // Une méthode de tri pour que ça soit propre dans le front
    List<GroupeNatureEntity> findAllByOrderByIdAsc();
}