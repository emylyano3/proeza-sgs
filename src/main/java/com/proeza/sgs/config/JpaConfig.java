package com.proeza.sgs.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
//@EnableCaching
public class JpaConfig {

	//	@Bean
	//	public CacheManager cacheManager () {
	//		EhCacheCacheManager cacheManager = new EhCacheCacheManager();
	//		cacheManager.setCacheManager(net.sf.ehcache.CacheManager.getInstance());
	//		return cacheManager;
	//	}

	@Bean
	public AbstractJpaVendorAdapter jpaVendorAdapter () {
		final HibernateJpaVendorAdapter jpaAdapter = new HibernateJpaVendorAdapter();
		jpaAdapter.setDatabasePlatform("org.hibernate.dialect.DB2Dialect");
		return jpaAdapter;
	}

	@Bean
	public Properties jpaProperties () {
		final Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.hbm2ddl.auto", "create-drop");
		jpaProperties.put("hibernate.hbm2ddl.import_files", "/import.sql,/notengoImport.sql");
		jpaProperties.put("hibernate.show_sql", "true");
		jpaProperties.put("hbm2ddl.auto", "create-drop");
		jpaProperties.put("hibernate.cache.use_second_level_cache", "true");
		jpaProperties.put("hibernate.cache.use_query_cache", "true");
		jpaProperties.put("hibernate.generate_statistics", "true");
		jpaProperties.put("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
		return jpaProperties;
	}

	@Bean
	public AbstractEntityManagerFactoryBean entityManager (DataSource ds, AbstractJpaVendorAdapter jpaAdapter, Properties jpaProperties) {
		final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setPackagesToScan("com.proeza.sgs.business.entity", "com.proeza.security.entity", "com.proeza.sgs.system.entity");
		em.setDataSource(ds);
		em.setJpaVendorAdapter(jpaAdapter);
		em.setJpaProperties(jpaProperties);
		return em;
	}

	@Bean
	public PlatformTransactionManager transactionManager (DataSource ds, EntityManagerFactory em) {
		final JpaTransactionManager tm = new JpaTransactionManager();
		tm.setDataSource(ds);
		tm.setEntityManagerFactory(em);
		return tm;
	}
}