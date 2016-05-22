package com.proeza.sgs.config.init;

import java.io.IOException;

import javax.servlet.Filter;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.proeza.sgs.config.ConfigConsts;
import com.proeza.sgs.config.dispatcher.WebMvcConfig;
import com.proeza.sgs.config.env.Environments;
import com.proeza.sgs.config.root.ContextConfig;

public class MvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    private static final String APPLICATION_PROPERTIES = "application.properties";

    {
        try {
            ClassPathResource cpr = new ClassPathResource(APPLICATION_PROPERTIES);
            System.getProperties().load(cpr.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException("Error iniciando la aplicacion. No se puede cargar el archivo " + APPLICATION_PROPERTIES, e);
        }
    }

    @Override
    protected Class<?>[] getRootConfigClasses () {
        return new Class<?>[] {Environments.class, ContextConfig.class};
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
        encodingFilter.setEncoding(ConfigConsts.DEFAULT_ENCODING);
        encodingFilter.setForceEncoding(true);
        return new Filter[] {encodingFilter};
    }
}