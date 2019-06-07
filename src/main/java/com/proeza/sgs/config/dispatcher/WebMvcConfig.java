package com.proeza.sgs.config.dispatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.proeza.core.config.GeneralSettings;
import com.proeza.sgs.web.interceptor.UserLoggedInterceptor;

@Configuration
@ComponentScan(basePackages = {
    "com.proeza.sgs.web",
    "com.proeza.security.rest"
})
@EnableWebMvc
@EnableTransactionManagement
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private GeneralSettings generalSettings;

	@Override
	public void addResourceHandlers (ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Override
	public void addInterceptors (InterceptorRegistry registry) {
		registry.addInterceptor(userLoggedInterceptor());
	}

	@Bean
	public UserLoggedInterceptor userLoggedInterceptor () {
		return new UserLoggedInterceptor();
	}

	@Override
	public void configureDefaultServletHandling (DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addCorsMappings (CorsRegistry registry) {
		registry.addMapping("*").allowedOrigins(this.generalSettings.getCrossOriginSource());
	}

	@Bean
	public CommonsMultipartResolver multipartResolver () {
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		commonsMultipartResolver.setMaxUploadSize(new Long(this.generalSettings.getMaxUploadSize()));
		return commonsMultipartResolver;
	}
}