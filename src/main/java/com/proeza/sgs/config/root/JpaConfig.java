package com.proeza.sgs.config.root;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.proeza.core.config.JpaSettings;
import com.proeza.core.persistence.QueryRegistry;

import static org.hibernate.cfg.AvailableSettings.*;

@Configuration
@ComponentScan(
        basePackages = {
                "com.proeza.**.dao.**"
        })
public class JpaConfig {

    @Autowired
    private JpaSettings jpaSettings;

    @Bean
    public AbstractJpaVendorAdapter jpaVendorAdapter() {
        final AbstractJpaVendorAdapter jpaAdapter = new HibernateJpaVendorAdapter();
        jpaAdapter.setDatabasePlatform(this.jpaSettings.getDialect());
        return jpaAdapter;
    }

    @Bean
    public Properties jpaProperties() {
        final Properties jpaProperties = new Properties();
        jpaProperties.put(HBM2DDL_AUTO, this.jpaSettings.getHbm2ddlAuto());
        jpaProperties.put(HBM2DDL_IMPORT_FILES, this.jpaSettings.getHbm2ddlImportFiles());
        jpaProperties.put(SHOW_SQL, this.jpaSettings.getShowSql());
        jpaProperties.put(GENERATE_STATISTICS, this.jpaSettings.getGenerateStatistics());
        jpaProperties.put(USE_SECOND_LEVEL_CACHE, this.jpaSettings.getUseSecondLevelCache());
        jpaProperties.put(USE_QUERY_CACHE, this.jpaSettings.getUseQueryCache());
        jpaProperties.put(CACHE_REGION_FACTORY, this.jpaSettings.getCacheRegionFactoryClass());
        jpaProperties.put(CURRENT_SESSION_CONTEXT_CLASS, this.jpaSettings.getCurrentSessionContextClass());
        return jpaProperties;
    }

    @Bean
    public AbstractEntityManagerFactoryBean entityManager(DataSource ds, AbstractJpaVendorAdapter jpaAdapter, Properties jpaProperties) {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setPackagesToScan(
                "com.proeza.security.entity",
                "com.proeza.sgs.business.entity",
                "com.proeza.sgs.system.entity",
                "com.proeza.core.i18n.entity",
                "com.proeza.core.error.entity",
                "com.proeza.core.tracking.entity");
        em.setDataSource(ds);
        em.setJpaVendorAdapter(jpaAdapter);
        em.setJpaProperties(jpaProperties);
        em.setMappingResources("queries/named/crones/relevamientoStock.hbm.xml");
        return em;
    }

    @Bean
    public QueryRegistry queriesRegistry() {
        return new QueryRegistry("/queries");
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource ds, AbstractEntityManagerFactoryBean emf) {
        final JpaTransactionManager tm = new JpaTransactionManager();
        tm.setDataSource(ds);
        tm.setEntityManagerFactory(emf.getObject());
        return tm;
    }
}