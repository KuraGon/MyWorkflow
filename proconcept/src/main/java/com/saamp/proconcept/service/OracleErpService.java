package com.saamp.proconcept.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class OracleErpService {

    // Injecte le JdbcTemplate configuré pour la BDD Oracle
    private final JdbcTemplate oracleJdbcTemplate;

    // Le paramètre clé pour l'initialisation de la session
    private static final String SESSION_CONTEXT = "TEST20250901_SAAMP_LOG";

    public OracleErpService(@Qualifier("oracleJdbcTemplate") JdbcTemplate oracleJdbcTemplate) {
        this.oracleJdbcTemplate = oracleJdbcTemplate;
    }

    /**
     * Initialise le contexte de session dans Oracle.
     */
    private void initialiserSession() {
        // Bloc PL/SQL pour l'exécution de la procédure InitSession
        String initSql = """
            begin
                PCS.PC_INIT_SESSION.InitSession(?, 'SUPPORT', null, 'DEFAULT');
            end;
            """;

        // Exécution de la commande avec le paramètre
        oracleJdbcTemplate.update(initSql, SESSION_CONTEXT);
    }

    /**
     * Exécute la séquence complète: Initialisation de session, puis récupération des paramètres.
     * @return Les informations de paramètres de rapport (company_owner, company_name, company_id).
     */
    public Map<String, Object> getRapportParameters() {
        // 1. Initialisation (CRUCIAL : Doit être fait avant le SELECT)
        initialiserSession();

        // 2. Requête SELECT utilisant les fonctions contextuelles
        String selectSql = """
            select
                PCS.PC_INIT_SESSION.GETCOMPANYOWNER() as company_owner,
                PCS.PC_INIT_SESSION.GETCOMNAME() as company_name,
                PCS.PC_INIT_SESSION.COMPANY_ID() as company_id
            from
                dual
            """;

        // Comme la requête sélectionne à partir de 'dual', elle retourne une seule ligne
        return oracleJdbcTemplate.queryForMap(selectSql);
    }
}