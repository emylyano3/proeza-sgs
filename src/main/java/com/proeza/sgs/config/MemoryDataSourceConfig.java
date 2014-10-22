package com.proeza.sgs.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.derby.jdbc.EmbeddedDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;

@Configuration
@Profile(value = { "dev", "test" })
@EnableTransactionManagement
@ComponentScan(basePackages = "com.proeza", excludeFilters = { @Filter(Configuration.class) })
@PropertySource("classpath:com/proeza/sgs/config/application.properties")
@Import({ SecurityConfig.class })
public class MemoryDataSourceConfig {

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource ds = new DriverManagerDataSource("jdbc:derby:target/database/testDB;create=true",
                "app", "app");
        ds.setDriverClassName(EmbeddedDriver.class.getName());
        return ds;
    }

    @Bean
    public AbstractJpaVendorAdapter jpaVendorAdapter() {
        final HibernateJpaVendorAdapter jpaAdapter = new HibernateJpaVendorAdapter();
        jpaAdapter.setDatabasePlatform("org.hibernate.dialect.DB2Dialect");
        return jpaAdapter;
    }

    @Bean
    public Properties jpaProperties() {
        final Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.hbm2ddl.auto", "create-drop");
        jpaProperties.put("hibernate.show_sql", "true");
        jpaProperties.put("hbm2ddl.auto", "create-drop");
        return jpaProperties;
    }

    @Bean
    public AbstractEntityManagerFactoryBean entityManager(DataSource ds, AbstractJpaVendorAdapter jpaAdapter,
            Properties jpaProperties) {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setPackagesToScan("com.proeza.sgs.business.entity", "com.proeza.security.entity");
        em.setDataSource(ds);
        em.setJpaVendorAdapter(jpaAdapter);
        em.setJpaProperties(jpaProperties);
        return em;
    }

    @Bean
    public AbstractPlatformTransactionManager transactionManager(DataSource ds, EntityManagerFactory em) {
        final JpaTransactionManager tm = new JpaTransactionManager();
        tm.setDataSource(ds);
        tm.setEntityManagerFactory(em);
        return tm;
    }

    @Bean
    public PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}