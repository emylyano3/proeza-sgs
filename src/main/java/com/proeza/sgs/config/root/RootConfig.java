package com.proeza.sgs.config.root;

import javax.sql.DataSource;

import nz.net.ultraq.thymeleaf.LayoutDialect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;
import org.thymeleaf.extras.springsecurity3.dialect.SpringSecurityDialect;
import org.thymeleaf.messageresolver.IMessageResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.messageresolver.SpringMessageResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import com.proeza.core.config.DataSourceSettings;
import com.proeza.core.config.MailSettings;
import com.proeza.core.resources.MessageResolver;

@Configuration
@ComponentScan(basePackages = "com.proeza", excludeFilters = {
	@Filter(Configuration.class),
	@Filter(Controller.class)
})
@EnableAsync
@EnableScheduling
@EnableTransactionManagement
public class RootConfig {

	@Autowired
	private DataSourceSettings	dsSettings;

	@Autowired
	private MailSettings		mailSettings;

	@Bean
	public DataSource dataSource () {
		final DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setUrl(this.dsSettings.getUrl());
		ds.setUsername(this.dsSettings.getUserName());
		ds.setPassword(this.dsSettings.getPass());
		ds.setDriverClassName(this.dsSettings.getDriver());
		return ds;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer () {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public MessageSource messageSource () {
		final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/WEB-INF/messages/messages");
		return messageSource;
	}

	@Bean
	public MessageResolver proezaMessageResolver (MessageSource messageSource, LocaleResolver localeResolver) {
		return new MessageResolver(messageSource, localeResolver);
	}

	@Bean
	public LocaleResolver localeResolver () {
		return new FixedLocaleResolver();
	}

	@Bean
	public ViewResolver viewResolver (SpringTemplateEngine templateEngine) {
		final ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine);
		return viewResolver;
	}

	@Bean
	public IMessageResolver messageResolver (MessageSource messageSource) {
		final SpringMessageResolver messageResolver = new SpringMessageResolver();
		messageResolver.setMessageSource(messageSource);
		return messageResolver;
	}

	@Bean
	public TemplateResolver webTemplateResolver () {
		final TemplateResolver templateResolver = new ServletContextTemplateResolver();
		templateResolver.setPrefix("/WEB-INF/views/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML5");
		templateResolver.setOrder(2);
		return templateResolver;
	}

	@Bean
	public TemplateResolver emailTemplateResolver () {
		TemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix("mail/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML5");
		templateResolver.setOrder(1);
		return templateResolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine (IMessageResolver messageResolver) {
		final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.addTemplateResolver(webTemplateResolver());
		templateEngine.addTemplateResolver(emailTemplateResolver());
		templateEngine.addDialect(new LayoutDialect());
		templateEngine.addDialect(new SpringSecurityDialect());
		templateEngine.addMessageResolver(messageResolver);
		return templateEngine;
	}

	@Bean
	public JavaMailSender mailSender () {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(this.mailSettings.getHost());
		mailSender.setPort(this.mailSettings.getPort());
		mailSender.setPassword(this.mailSettings.getPassword());
		mailSender.setUsername(this.mailSettings.getUsername());
		mailSender.setProtocol(this.mailSettings.getProtocol());
		mailSender.setJavaMailProperties(this.mailSettings.asProperties());
		return mailSender;
	}
}