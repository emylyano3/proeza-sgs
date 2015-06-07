package com.proeza.sgs.config.root;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.proeza.core.config.DataSourceSettings;

@Configuration
public class DataSourceConfig {

    @Autowired
    private DataSourceSettings dsSettings;

    @Bean
    public DataSource dataSource () {
        final DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(this.dsSettings.getUrl());
        ds.setUsername(this.dsSettings.getUserName());
        ds.setPassword(this.dsSettings.getPass());
        ds.setDriverClassName(this.dsSettings.getDriver());
        return ds;
    }
}