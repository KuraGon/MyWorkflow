package com.saamp.workflow.repository;

import com.querydsl.core.BooleanBuilder;
import com.saamp.workflow.entity.PhoneContactEntity;
import com.saamp.workflow.entity.PhoneUserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface PhoneUserRepository extends JpaRepository<PhoneUserEntity, UUID>,
        QuerydslPredicateExecutor<PhoneUserEntity> {

    @Query("""
        select pu
        from PhoneUserEntity pu
        left join pu.phonePro ppro
        left join pu.phonePerso pperso
        left join pu.phoneTelavox ptelavox
        where
          (:q is null)
          or (:q = '')
          or (
              lower(coalesce(pu.firstname, '')) like lower(concat('%', :q, '%'))
              or lower(coalesce(pu.lastname, '')) like lower(concat('%', :q, '%'))
              or lower(coalesce(pu.denomination, '')) like lower(concat('%', :q, '%'))
              or lower(coalesce(pu.company, '')) like lower(concat('%', :q, '%'))
              or coalesce(ppro.phoneNumber, '') like concat('%', :q, '%')
              or coalesce(pperso.phoneNumber, '') like concat('%', :q, '%')
              or coalesce(ptelavox.phoneNumber, '') like concat('%', :q, '%')
          )
        """)
    Page<PhoneUserEntity> findPageByFilter(@Param("q") String q, Pageable pageable);

    @Query("""
        select pu
        from PhoneUserEntity pu
        where pu.phonePro = :phoneContact
           or pu.phonePerso = :phoneContact
           or pu.phoneTelavox = :phoneContact
    """)
    Optional<PhoneUserEntity> findByPhoneContact(
            @Param("phoneContact") PhoneContactEntity phoneContact
    );

    @Query("""
    select distinct pu
    from PhoneUserEntity pu
    where pu.phonePro.id in :contactIds
       or pu.phoneOther.id in :contactIds
       or pu.phonePerso.id in :contactIds
       or pu.phoneTelavox.id in :contactIds
""")
    List<PhoneUserEntity> findAllByAnyPhoneContactIdIn(
            @Param("contactIds") Collection<UUID> contactIds
    );

    @Query("""
    select (count(pu) > 0)
    from PhoneUserEntity pu
    where pu.phonePro.id = :contactId
       or pu.phoneOther.id = :contactId
       or pu.phonePerso.id = :contactId
       or pu.phoneTelavox.id = :contactId
""")
    boolean existsByAnyPhoneContactId(@Param("contactId") UUID contactId);

    Optional<PhoneUserEntity> findByIdAndMysaampIdClientIsNotNull(UUID id);

    @Query(
            value = """
    select distinct pu
    from PhoneUserEntity pu
    left join pu.phonePro pro
    left join pu.phonePerso perso
    left join pu.phoneTelavox telavox
  """,
            countQuery = """
    select count(distinct pu.id)
    from PhoneUserEntity pu
    left join pu.phonePro pro
    left join pu.phonePerso perso
    left join pu.phoneTelavox telavox
  """
    )
    Page<PhoneUserEntity> search(Pageable pageable, BooleanBuilder predicate);

}
