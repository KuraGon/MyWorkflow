package com.saamp.workflow.repository;

import com.saamp.workflow.entity.PhoneLogEntity;
import com.saamp.workflow.entity.PhoneUserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface PhoneLogRepository extends JpaRepository<PhoneLogEntity, UUID> {

    // Tous les logs pour un forfait donné, du plus récent au plus ancien
    List<PhoneLogEntity> findByForfaitContactIdInOrderByEventDateDesc(
            Collection<UUID> forfaitContactIds
    );

    @Query("""
                select pu
                from PhoneUserEntity pu
                where exists (
                    select 1
                    from PhoneLogEntity l
                    where l.forfaitContact is not null
                      and l.forfaitContact in (pu.phonePro, pu.phonePerso, pu.phoneTelavox)
                )
                order by pu.lastname, pu.firstname
            """)
    List<PhoneUserEntity> findDistinctForfaitUsers();

    // Pagination
    Page<PhoneLogEntity> findByForfaitContactIdIn(Collection<UUID> forfaitContactId, Pageable pageable);

    Page<PhoneLogEntity> findByForfaitContactIdInAndEventDateBetween(
            Collection<UUID> forfaitContactIds,
            LocalDateTime start,
            LocalDateTime end,
            Pageable pageable
    );

    // ==========================
    // ✅ STATS GLOBAL (pas lié à la pagination)
    // ==========================

    interface CallStatsAgg {
        String getCorrespondent();

        String getCorrespondentLastName();

        String getCorrespondentFirstName();

        String getCorrespondentCompany();

        Long getCallCount();

        Long getTotalDurationSec();

        Double getTotalCostHt();
    }

    interface SmsStatsAgg {
        String getCorrespondent();

        String getCorrespondentLastName();

        String getCorrespondentFirstName();

        String getCorrespondentCompany();

        Long getSmsCount();

        Double getTotalCostHt();
    }

    @Query(
            value = """
            with base as (

              -- 1) sortants : caller ∈ contactIds, dest ∉ contactIds
              select
                  l.caller_contact_id,
                  l.destination_contact_id,
                  l.event_date,
                  l.type,
                  l.direction,
                  coalesce(l.duration_sec, 0) as duration_sec,
                  coalesce(l.cost_ht, 0)      as cost_ht,

                  dest.phone_number as correspondent,
                  pu_dest.lastname  as correspondent_lastname,
                  pu_dest.firstname as correspondent_firstname,
                  coalesce(pu_dest.company, pu_dest.denomination) as correspondent_company

              from phone_logs l
              join phone_contact dest on dest.id = l.destination_contact_id

              left join lateral (
                  select pu.lastname, pu.firstname, pu.company, pu.denomination
                  from phone_user pu
                  where pu.phone_pro_id = l.destination_contact_id
                     or pu.phone_perso_id = l.destination_contact_id
                     or pu.phone_telavox_id = l.destination_contact_id
                     or pu.phone_other_id = l.destination_contact_id
                  limit 1
              ) pu_dest on true

              where l.caller_contact_id = any(cast(:contactIds as uuid[]))
                and not (l.destination_contact_id = any(cast(:contactIds as uuid[])))
                and upper(l.type) <> 'SMS'
                and (:start is null or l.event_date >= cast(:start as timestamp))
                and (:end   is null or l.event_date <= cast(:end   as timestamp))

              union all

              -- 2) entrants : dest ∈ contactIds, caller ∉ contactIds
              select
                  l.caller_contact_id,
                  l.destination_contact_id,
                  l.event_date,
                  l.type,
                  l.direction,
                  coalesce(l.duration_sec, 0) as duration_sec,
                  coalesce(l.cost_ht, 0)      as cost_ht,

                  caller.phone_number as correspondent,
                  pu_caller.lastname  as correspondent_lastname,
                  pu_caller.firstname as correspondent_firstname,
                  coalesce(pu_caller.company, pu_caller.denomination) as correspondent_company

              from phone_logs l
              join phone_contact caller on caller.id = l.caller_contact_id

              left join lateral (
                  select pu.lastname, pu.firstname, pu.company, pu.denomination
                  from phone_user pu
                  where pu.phone_pro_id = l.caller_contact_id
                     or pu.phone_perso_id = l.caller_contact_id
                     or pu.phone_telavox_id = l.caller_contact_id
                     or pu.phone_other_id = l.caller_contact_id
                  limit 1
              ) pu_caller on true

              where l.destination_contact_id = any(cast(:contactIds as uuid[]))
                and not (l.caller_contact_id = any(cast(:contactIds as uuid[])))
                and upper(l.type) <> 'SMS'
                and (:start is null or l.event_date >= cast(:start as timestamp))
                and (:end   is null or l.event_date <= cast(:end   as timestamp))
            ),

            dedup as (
              -- Dédoublonnage : l’import peut contenir Emis + Recu pour le même appel (mêmes champs)
              select distinct on (
                  b.caller_contact_id,
                  b.destination_contact_id,
                  b.event_date,
                  upper(b.type),
                  b.duration_sec,
                  b.cost_ht
              )
                  b.*
              from base b
              order by
                  b.caller_contact_id,
                  b.destination_contact_id,
                  b.event_date,
                  upper(b.type),
                  b.duration_sec,
                  b.cost_ht,
                  case when upper(coalesce(b.direction,'')) in ('EMIS','SORTANT') then 0 else 1 end
            )

            select
                d.correspondent as correspondent,
                max(d.correspondent_lastname)  as correspondentLastName,
                max(d.correspondent_firstname) as correspondentFirstName,
                max(d.correspondent_company)   as correspondentCompany,
                count(*) as callCount,
                coalesce(sum(d.duration_sec), 0) as totalDurationSec,
                coalesce(sum(d.cost_ht), 0)      as totalCostHt
            from dedup d
            where d.correspondent is not null
            group by d.correspondent
            order by totalDurationSec desc
            """,
            nativeQuery = true
    )
    List<CallStatsAgg> computeGlobalCallStats(
            @Param("contactIds") UUID[] contactIds,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    @Query(
            value = """
            select
                case
                    when l.caller_contact_id = any(cast(:contactIds as uuid[])) then dest.phone_number
                    else caller.phone_number
                end as correspondent,
                count(l.id) as smsCount
            from phone_logs l
            join phone_contact dest on dest.id = l.destination_contact_id
            join phone_contact caller on caller.id = l.caller_contact_id
            where (l.caller_contact_id = any(cast(:contactIds as uuid[])) or l.destination_contact_id = any(cast(:contactIds as uuid[])))
              and upper(l.type) = 'SMS'
              and (:start is null or l.event_date >= cast(:start as timestamp))
              and (:end   is null or l.event_date <= cast(:end   as timestamp))
            group by correspondent
            order by smsCount desc
            """,
            nativeQuery = true
    )
    List<SmsStatsAgg> computeGlobalSmsStats(
            @Param("contactIds") UUID[] contactIds,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );


    // ==========================
    // ✅ KPIs Commercial
    // ==========================

    interface SalesRepKpiProjection {
        Long getTotalCallDurationSec();

        Long getTotalCalls();

        Long getUniqueProspects();

        Long getTotalInteractions();

        Long getClientInteractions();

        Long getInternalInteractions();

        Long getUnknownInteractions();

        Long getTotalCallWeekendAndNight();
    }

    interface SalesRepHourActivityProjection {
        Integer getHour();

        Long getCallCount();

        Long getSmsCount();

        Long getTotalCount();
    }

    /**
     * KPIs “par commercial” sur toute la période filtrée.
     * <p>
     * Ancienne classification via phone_contacts.company.
     * Nouvelle classification via phone_user (match des numéros par phone_pro_id / phone_perso_id / phone_telavox_id).
     * <p>
     * - interne : company/denomination = 'saamp' (case-insensitive)
     * - inconnu : pas de phone_user (ou company/denomination null)
     * - client  : company/denomination non null && != 'saamp'
     * <p>
     * ⚠️ Fix Postgres : on évite "(? is null or ...)" qui pose pb de typage => coalesce(timestamp)
     * ⚠️ On utilise LATERAL + LIMIT 1 pour éviter les doublons si un numéro est lié à plusieurs phone_user.
     */
    @Query(
            value = """
                    select
                      coalesce(sum(case when upper(t.type) <> 'SMS' then coalesce(t.duration_sec, 0) else 0 end), 0) as totalCallDurationSec,
                      coalesce(sum(case when upper(t.type) <> 'SMS' then 1 else 0 end), 0) as totalCalls,
                    
                      coalesce(count(distinct case
                            when t.corr_number is not null
                             and t.corr_is_client = true
                            then t.corr_number end), 0) as uniqueProspects,
                    
                      coalesce(count(*), 0) as totalInteractions,
                    
                      coalesce(sum(case when t.corr_is_client = true then 1 else 0 end), 0) as clientInteractions,
                      coalesce(sum(case when t.corr_is_internal = true then 1 else 0 end), 0) as internalInteractions,
                      coalesce(sum(case when t.corr_is_unknown = true then 1 else 0 end), 0) as unknownInteractions,
                    
                      coalesce(sum(
                        case
                          when upper(t.type) <> 'SMS'
                           and (
                                extract(isodow from t.event_date) in (6, 7)
                                or extract(hour from t.event_date) < 7
                                or extract(hour from t.event_date) >= 21
                           )
                          then 1
                          else 0
                        end
                      ), 0) as totalCallWeekendAndNight
                    from (
                      select
                        case
                          when pl.forfait_contact_id = caller.id then dest.phone_number
                          when pl.forfait_contact_id = dest.id then caller.phone_number
                          else null
                        end as corr_number,
                    
                        case
                          when pl.forfait_contact_id = caller.id
                               then coalesce(dest_u.company, dest_u.denomination)
                          when pl.forfait_contact_id = dest.id
                               then coalesce(caller_u.company, caller_u.denomination)
                          else null
                        end as corr_company,
                    
                        case
                          when (
                            case
                              when pl.forfait_contact_id = caller.id
                                   then coalesce(dest_u.company, dest_u.denomination)
                              when pl.forfait_contact_id = dest.id
                                   then coalesce(caller_u.company, caller_u.denomination)
                              else null
                            end
                          ) is null then true
                          else false
                        end as corr_is_unknown,
                    
                        case
                          when lower(trim(coalesce(
                            case
                              when pl.forfait_contact_id = caller.id
                                   then coalesce(dest_u.company, dest_u.denomination)
                              when pl.forfait_contact_id = dest.id
                                   then coalesce(caller_u.company, caller_u.denomination)
                              else null
                            end
                          , ''))) = 'saamp' then true
                          else false
                        end as corr_is_internal,
                    
                        case
                          when (
                            case
                              when pl.forfait_contact_id = caller.id
                                   then coalesce(dest_u.company, dest_u.denomination)
                              when pl.forfait_contact_id = dest.id
                                   then coalesce(caller_u.company, caller_u.denomination)
                              else null
                            end
                          ) is not null
                          and lower(trim(
                            case
                              when pl.forfait_contact_id = caller.id
                                   then coalesce(dest_u.company, dest_u.denomination)
                              when pl.forfait_contact_id = dest.id
                                   then coalesce(caller_u.company, caller_u.denomination)
                              else null
                            end
                          )) <> 'saamp'
                          then true
                          else false
                        end as corr_is_client,
                    
                        pl.type,
                        pl.duration_sec,
                        pl.event_date
                      from phone_logs pl
                        left join phone_contact dest on dest.id = pl.destination_contact_id
                        left join phone_contact caller on caller.id = pl.caller_contact_id
                    
                        left join lateral (
                          select pu.company, pu.denomination
                          from phone_user pu
                          where pu.phone_pro_id = dest.id
                             or pu.phone_perso_id = dest.id
                             or pu.phone_telavox_id = dest.id
                          limit 1
                        ) dest_u on true
                    
                        left join lateral (
                          select pu.company, pu.denomination
                          from phone_user pu
                          where pu.phone_pro_id = caller.id
                             or pu.phone_perso_id = caller.id
                             or pu.phone_telavox_id = caller.id
                          limit 1
                        ) caller_u on true
                    
                      where pl.forfait_contact_id in (:contactIds)
                        and pl.event_date >= coalesce(cast(:startDate as timestamp), '-infinity'::timestamp)
                        and pl.event_date <= coalesce(cast(:endDate as timestamp), 'infinity'::timestamp)
                    ) t
                    """,
            nativeQuery = true
    )
    SalesRepKpiProjection computeSalesRepKpis(
            @Param("contactIds") Collection<UUID> contactIds,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    /**
     * Activité par heure, split Appels/SMS (trié par heure ASC).
     */
    @Query(
            value = """
                    select
                      cast(extract(hour from pl.event_date) as int) as hour,
                      coalesce(sum(case when upper(pl.type) <> 'SMS' then 1 else 0 end), 0) as callCount,
                      coalesce(sum(case when upper(pl.type) = 'SMS' then 1 else 0 end), 0) as smsCount,
                      count(*) as totalCount
                    from phone_logs pl
                    where pl.forfait_contact_id in (:contactIds)
                      and pl.event_date >= coalesce(cast(:startDate as timestamp), '-infinity'::timestamp)
                      and pl.event_date <= coalesce(cast(:endDate as timestamp), 'infinity'::timestamp)
                    group by 1
                    order by 1 asc
                    """,
            nativeQuery = true
    )
    List<SalesRepHourActivityProjection> computeSalesRepHourActivitySplit(
            @Param("contactIds") Collection<UUID> contactIds,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );
}