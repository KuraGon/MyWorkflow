package com.saamp.olddb.config;

import javax.sql.DataSource;

import jakarta.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.saamp.olddb.repository",
        entityManagerFactoryRef = "oldDbEntityManagerFactory",
        transactionManagerRef = "oldDbTransactionManager"
)
public class OldDbConfig {

    @Bean(name = "oldDbDataSource")
    @ConfigurationProperties(prefix = "spring.olddb")
    public DataSource oldDbDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "oldDbEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean oldDbEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("oldDbDataSource") DataSource dataSource
    ) {
        return builder
                .dataSource(dataSource)
                .packages("com.saamp.olddb.entity") // <--- **CRUCIAL**
                .persistenceUnit("olddb")
                .properties(Map.of(
                        "hibernate.hbm2ddl.auto", "none",
                        "hibernate.dialect", "org.hibernate.dialect.MySQLDialect"
                ))
                .build();
    }

    @Bean(name = "oldDbTransactionManager")
    public PlatformTransactionManager oldDbTransactionManager(
            @Qualifier("oldDbEntityManagerFactory") EntityManagerFactory emf
    ) {
        return new JpaTransactionManager(emf);
    }
}
