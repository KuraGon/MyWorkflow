package com.saamp.olddb.repository;

import com.saamp.olddb.entity.Statistique;
import com.saamp.olddb.entity.StatistiqueKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface StatistiqueRepository extends JpaRepository<Statistique, StatistiqueKey> {
    interface CaHmByClientAgg {
        Long getClIdent();
        BigDecimal getCaHm();
    }

    @Query(value = """
        SELECT
          s.cl_ident AS clIdent,
          COALESCE(SUM(s.CA_HM), 0) AS caHm
        FROM Statistiques s
        WHERE s.annee = :annee
          AND s.mois = :mois
        GROUP BY s.cl_ident
        """, nativeQuery = true)
    List<CaHmByClientAgg> sumCaHmByClient(@Param("annee") int annee,
                                          @Param("mois") int mois);
}
