package com.saamp.olddb.repository;

import com.saamp.olddb.entity.OldContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface OldContactRepository extends JpaRepository<OldContact, Integer> {
    // Projection simple pour les infos qui nous intéressent
    interface ContactView {
        String getId();
        Long getClId();
        String getLastname();
        String getFirstname();
        String getDenomination();
        String getCompany();
        String getPhone();
        String getAutreTelephone();
        String getMobilePhone();
    }
    /**
     * SOURCE 1 : table contact + join client
     * Comparaison sur numéros NORMALISÉS côté SQL
     */
    @Query(value = """
        SELECT 
            c.id                  AS id,
            c.cl_ident            AS clId,
            c.Nom                 AS lastname,
            c.Prenom              AS firstname,
            NULL                  AS denomination,
            cl.Nom                AS company,
            c.Phone               AS phone,
            c.Autre_telephone     AS autreTelephone,
            c.MobilePhone         AS mobilePhone
        FROM contact c
        INNER JOIN client cl ON cl.cl_ident = c.cl_ident
        WHERE REGEXP_REPLACE(c.Phone, '[^0-9]', '') = :digits
           OR REGEXP_REPLACE(c.Autre_telephone, '[^0-9]', '') = :digits
           OR REGEXP_REPLACE(c.MobilePhone, '[^0-9]', '') = :digits
        LIMIT 10
        """,
            nativeQuery = true)
    List<ContactView> searchByAnyPhone(@Param("digits") String digits);

    /**
     * SOURCE 2 : table client uniquement
     * Comparaison sur téléphone NORMALISÉ côté SQL
     */
    @Query(value = """
        SELECT 
            cl.id               AS id,
            cl.cl_ident          AS clId,
            NULL                AS lastname,
            NULL                AS firstname,
            cl.Nom              AS denomination,
            cl.raison_sociale   AS company,
            cl.Tel              AS phone,
            NULL                AS autreTelephone,
            NULL                AS mobilePhone
        FROM client cl
        WHERE REGEXP_REPLACE(cl.Tel, '[^0-9]', '') = :digits
        LIMIT 10
        """,
            nativeQuery = true)
    List<ContactView> searchByAnyPhoneInClTable(@Param("digits") String digits);
}
