package com.proeza.sgs.config.env;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;

import com.proeza.core.config.Settings;

@Profile("dev")
@Configuration
@ComponentScan(basePackageClasses = {Settings.class})
public class Dev {

	@Bean
	public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer () {
		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
		ppc.setLocation(new ClassPathResource("env/dev/app-config.properties"));
		return ppc;
	}
}