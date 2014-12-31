package com.proeza.sgs.config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.proeza.sgs.config.dispatcher.WebMvcConfig;
import com.proeza.sgs.config.root.JpaConfig;
import com.proeza.sgs.config.root.RootConfig;
import com.proeza.sgs.config.root.SecurityConfig;

public class MvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses () {
		return new Class<?>[] {RootConfig.class, JpaConfig.class, SecurityConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses () {
		return new Class<?>[] {WebMvcConfig.class};
	}

	@Override
	protected String[] getServletMappings () {
		return new String[] {"/"};
	}

	@Override
	protected Filter[] getServletFilters () {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		encodingFilter.setForceEncoding(true);
		return new Filter[] {encodingFilter};
	}
}