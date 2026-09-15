package com.saamp.workflow.repository;

import com.saamp.workflow.entity.PhoneContactEntity;
import com.saamp.workflow.entity.PhoneUserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository d'accès aux contacts téléphoniques.
 */
public interface PhoneContactRepository extends JpaRepository<PhoneContactEntity, UUID> {

    Optional<PhoneContactEntity> findByPhoneNumber(String phoneNumber);

    @Query("""
        select pc
        from PhoneContactEntity pc
        where
          (:q is null)
          or (:q = '')
          or (pc.phoneNumber like concat('%', :q, '%'))
        """)
    Page<PhoneContactEntity> findPageByFilter(@Param("q") String q, Pageable pageable);

    /**
     * Contacts qui ne sont rattachés à aucun phone_user (tous rôles confondus).
     * On les enrichit en créant un phone_user "client" lié au contact.
     */
    @Query("""
        select pc
        from PhoneContactEntity pc
        where not exists (
            select 1
            from PhoneUserEntity pu
            where pu.phonePro = pc
               or pu.phoneOther = pc
               or pu.phonePerso = pc
               or pu.phoneTelavox = pc
        )
        order by pc.id
    """)
    List<PhoneContactEntity> findUnmappedContacts(Pageable pageable);
}
