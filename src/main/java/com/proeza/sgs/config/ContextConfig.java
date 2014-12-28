package com.proeza.sgs.config;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContextConfig {

	@Bean
	public ValidatorFactory validatorFactory () {
		return Validation.buildDefaultValidatorFactory();
	}
}