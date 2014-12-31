package com.proeza.sgs.config.root;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.proeza.core.config.DataSourceSettings;

/**
 * Main configuration class for the application. Turns on @Component scanning, loads externalized
 * application.properties, and sets up the database.
 * 
 * @author Craig Walls
 */
@Configuration
@ComponentScan(basePackages = "com.proeza", excludeFilters = {
	@Filter(Configuration.class),
	@Filter(Controller.class)
})
@EnableTransactionManagement
public class RootConfig {

	@Autowired
	private DataSourceSettings	dsSettings;

	@Bean
	public DataSource dataSource () {
		final DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setUrl(this.dsSettings.getUrl());
		ds.setUsername(this.dsSettings.getUserName());
		ds.setPassword(this.dsSettings.getPass());
		ds.setDriverClassName(this.dsSettings.getDriver());
		return ds;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer () {
		return new PropertySourcesPlaceholderConfigurer();
	}
}