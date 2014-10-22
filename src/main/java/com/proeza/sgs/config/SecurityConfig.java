package com.proeza.sgs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user")
                .password("password")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("admin")
                .roles("ADMIN", "USER");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login/authenticate")
                .failureUrl("/login?error=bad_credentials")
                .and()
                .logout()
                .logoutUrl("/signout")
                .deleteCookies("JSESSIONID")
                .and()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/**").permitAll();
    }
    // @Autowired
    // private ApplicationContext context;
    //
    // @Autowired
    // private DataSource dataSource;
    //
    // @Autowired
    // public void registerAuthentication(AuthenticationManagerBuilder auth) throws Exception {
    // auth.jdbcAuthentication()
    // .dataSource(this.dataSource)
    // .usersByUsernameQuery("select nombre, password, true from Usuario where nombre = ?")
    // .authoritiesByUsernameQuery("select nombre, 'ROLE_USER' from Usuario where nombre = ?")
    // .passwordEncoder(passwordEncoder());
    // }
    //
    // @Override
    // public void configure(WebSecurity web) throws Exception {
    // web
    // .ignoring()
    // .antMatchers("/resources/**");
    // }
    //
    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    // http
    // .formLogin()
    // .loginPage("/login")
    // .loginProcessingUrl("/login/authenticate")
    // .failureUrl("/login?param.error=bad_credentials")
    // .and()
    // .logout()
    // .logoutUrl("/logout")
    // .deleteCookies("JSESSIONID")
    // .and()
    // .authorizeRequests()
    // .antMatchers("/login/**", "/logout/**")
    // ;
    // .permitAll()
    // .antMatchers("/**").authenticated()
    // .and()
    // .rememberMe();
    // }
    //
    // @Bean
    // public PasswordEncoder passwordEncoder() {
    // return NoOpPasswordEncoder.getInstance();
    // }
    //
    // @Bean
    // public TextEncryptor textEncryptor() {
    // return Encryptors.noOpText();
    // }

}
