package com.proeza.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DataSourceSettings extends Settings {

    @Value("${datasource.url}")
    private String url;

    @Value("${datasource.password}")
    private String pass;

    @Value("${datasource.username}")
    private String userName;

    @Value("${datasource.driver}")
    private String driver;

    public String getUrl () {
        return this.url;
    }

    public String getPass () {
        return this.pass;
    }

    public String getUserName () {
        return this.userName;
    }

    public String getDriver () {
        return this.driver;
    }
}