package com.proeza.sgs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.proeza.security.service.ProezaUserDetailsService;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private ProezaUserDetailsService	userDetailsService;

	@Override
	protected UserDetailsService userDetailsService () {
		return this.userDetailsService;
	}

	@Override
	public void configure (WebSecurity web) throws Exception {
		web
		.ignoring()
		.antMatchers("/resources/**");
	}

	@Override
	protected void configure (AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userDetailsService);
	}

	@Override
	protected void configure (HttpSecurity http) throws Exception {
		http
		.formLogin()
		.loginPage("/login")
		.loginProcessingUrl("/login/authenticate")
		.failureUrl("/login?error=bad_credentials")
		.and()
		.logout()
		.logoutUrl("/logout")
		.deleteCookies("JSESSIONID")
		.and()
		.authorizeRequests()
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/**").permitAll()
		.and()
		.csrf();
	}
}