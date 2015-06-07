package com.proeza.sgs.config.env;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;

@Profile("test")
@Configuration
public class Test {

    @Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer () {
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        ppc.setLocation(new ClassPathResource("env/test/app-config.properties"));
        ppc.setNullValue("");
        return ppc;
    }
}