package com.proeza.sgs.config.root;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.proeza.security.entity.Rol;
import com.proeza.sgs.system.dao.IPageDao;
import com.proeza.sgs.system.entity.Page;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String	ROLE_PREFIX	= "ROLE_";

	@Autowired
	private UserDetailsService	userDetailsService;

	@Autowired
	private IPageDao			pageDao;

	@Override
	public void configure (WebSecurity web) throws Exception {
		web
			.ignoring()
			.antMatchers("/resources/**");
	}

	@Override
	protected void configure (AuthenticationManagerBuilder auth) throws Exception {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(this.userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		auth.authenticationProvider(authenticationProvider);
	}

	@Bean
	public PasswordEncoder passwordEncoder () {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure (HttpSecurity http) throws Exception {
		addAccessControl(http
			.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/login/authenticate")
			.failureUrl("/login?error=bad_credentials")
			.and()
			.logout()
			.logoutUrl("/doLogout")
			.deleteCookies("JSESSIONID")
			.logoutSuccessUrl("/logout")
			.and()
			.csrf()
			.and());
	}

	private void addAccessControl (HttpSecurity http) throws Exception {
		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authRegister = http.authorizeRequests();
		List<Page> pages = this.pageDao.findAll();
		for (Page page : pages) {
			String pagePath = getPagePathPattern(page);
			Set<Rol> roles = page.getRoles();
			authRegister.antMatchers(pagePath).hasAnyRole(getRolesCodes(roles));
		}
		authRegister.antMatchers("/**").permitAll();
	}

	private String[] getRolesCodes (Set<Rol> roles) {
		List<String> rolesCodes = new ArrayList<>(roles.size());
		for (Rol rol : roles) {
			String roleCode = rol.getCodigo();
			if (roleCode.startsWith(ROLE_PREFIX)) {
				roleCode = roleCode.substring(ROLE_PREFIX.length());
			}
			rolesCodes.add(roleCode);
		}
		return rolesCodes.toArray(new String[rolesCodes.size()]);
	}

	private String getPagePathPattern (Page page) {
		StringBuilder pagePath = new StringBuilder();
		pagePath
			.append("/")
			.append(page.getGroup())
			.append("/")
			.append(page.getName());
		return pagePath.toString();
	}
}