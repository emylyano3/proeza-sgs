package com.proeza.sgs.config.init;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.Filter;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.proeza.sgs.config.dispatcher.WebMvcConfig;
import com.proeza.sgs.config.env.Dev;
import com.proeza.sgs.config.env.Prod;
import com.proeza.sgs.config.env.Test;
import com.proeza.sgs.config.root.JpaConfig;
import com.proeza.sgs.config.root.RootConfig;
import com.proeza.sgs.config.root.SecurityConfig;

public class MvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	private static final String	APPLICATION_PROPERTIES	= "application.properties";

	{
		ClassPathResource cpr = new ClassPathResource(APPLICATION_PROPERTIES);
		Properties p = System.getProperties();
		try {
			p.load(cpr.getInputStream());
		} catch (IOException e) {
			throw new RuntimeException("Error iniciando la aplicacion. No se puede cargar el archivo " + APPLICATION_PROPERTIES, e);
		}
	}

	@Override
	protected Class<?>[] getRootConfigClasses () {
		return new Class<?>[] {Dev.class, Test.class, Prod.class, RootConfig.class, JpaConfig.class, SecurityConfig.class};
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