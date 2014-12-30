package com.proeza.sgs.config;

import javax.sql.DataSource;

import org.apache.derby.jdbc.EmbeddedDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Main configuration class for the application. Turns on @Component scanning, loads externalized
 * application.properties, and sets up the database.
 * 
 * @author Craig Walls
 */
@Configuration
//@Profile(value = {"dev", "test"})
@ComponentScan(basePackages = "com.proeza", excludeFilters = {@Filter(Configuration.class)})
@PropertySource("classpath:com/proeza/sgs/config/application.properties")
@EnableTransactionManagement
public class MainConfig {

	@Bean
	public DataSource dataSource () {
		final DriverManagerDataSource ds = new DriverManagerDataSource("jdbc:derby:target/database/testDB;create=true", "app", "app");
		ds.setDriverClassName(EmbeddedDriver.class.getName());
		return ds;
	}

	@Bean
	public PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer () {
		return new PropertySourcesPlaceholderConfigurer();
	}
}