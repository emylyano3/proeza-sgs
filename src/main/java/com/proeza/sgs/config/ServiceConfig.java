package com.proeza.sgs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.proeza.security.dao.UsuarioDao;
import com.proeza.security.service.SpringSecIntegrationUserService;
import com.proeza.security.service.UserService;

@Configuration
public class ServiceConfig {

	@Bean
	public UserDetailsService userDetailsService (UsuarioDao dao) {
		return new SpringSecIntegrationUserService(dao);
	}

	@Bean
	public UserService userService (UsuarioDao dao) {
		return new UserService(dao);
	}
}