package com.proeza.sgs.config.root;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.messageresolver.IMessageResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.messageresolver.SpringMessageResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import com.proeza.core.config.GeneralSettings;
import com.proeza.core.config.MailSettings;
import com.proeza.core.resources.image.ImageManager;
import com.proeza.core.resources.message.MessageResolver;
import com.proeza.sgs.business.chart.ChartColor;
import com.proeza.sgs.business.chart.ChartColorManager;
import com.proeza.sgs.config.ConfigConsts;

import net.sf.ehcache.CacheException;

@Configuration
@Import(value = { DataSourceConfig.class, JpaConfig.class, SecurityConfig.class })
@ComponentScan(basePackages = { "com.proeza.core.config", "com.proeza.core.service", "com.proeza.core.tracking",
		"com.proeza.core.datamapper", "com.proeza.core.context", "com.proeza.core.classmapper",
		"com.proeza.security.dao", "com.proeza.security.service", "com.proeza.sgs.business", "com.proeza.sgs.system",
		"com.proeza.sgs.system.service", }, excludeFilters = { @Filter(Configuration.class), @Filter(Controller.class),
				@Filter(RestController.class) })
@EnableAsync
@EnableCaching
@EnableScheduling
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class ContextConfig {

	private static final String THYMELEAF_TEMPLATE_MODE = "HTML5";
	private static final String MESSAGES_LOCATION = "/WEB-INF/messages/messages";
	private static final String VIEWS_LOCATION = "/WEB-INF/views/";
	private static final String EHCACHE_CONFIG_LOCATION = "classpath:ehcache.xml";

	@Autowired
	private MailSettings mailSettings;

	@Autowired
	private GeneralSettings generalSettings;

	@Autowired
	private ApplicationContext context;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public MessageSource messageSource() {
		final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename(MESSAGES_LOCATION);
		messageSource.setDefaultEncoding(ConfigConsts.DEFAULT_ENCODING);
		return messageSource;
	}

	@Bean
	public com.proeza.core.resources.message.IMessageResolver proezaMessageResolver(MessageSource messageSource,
			LocaleResolver localeResolver) {
		return new MessageResolver(messageSource, localeResolver);
	}

	@Bean
	public LocaleResolver localeResolver() {
		return new FixedLocaleResolver();
	}

	@Bean
	public CacheManager cacheManager() throws CacheException, IOException {
		return new EhCacheCacheManager(
				net.sf.ehcache.CacheManager.create(this.context.getResource(EHCACHE_CONFIG_LOCATION).getInputStream()));
	}

	@Bean
	public ImageManager imageManager() {
		return new ImageManager();
	}

	@Bean
	public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
		final ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine);
		return viewResolver;
	}

	@Bean
	public IMessageResolver messageResolver(MessageSource messageSource) {
		final SpringMessageResolver messageResolver = new SpringMessageResolver();
		messageResolver.setMessageSource(messageSource);
		return messageResolver;
	}

	@Bean
	public TemplateResolver webTemplateResolver() {
		final TemplateResolver templateResolver = new ServletContextTemplateResolver();
		templateResolver.setPrefix(VIEWS_LOCATION);
		templateResolver.setTemplateMode(THYMELEAF_TEMPLATE_MODE);
		templateResolver.setCacheable(generalSettings.isViewCacheEnabled());
		templateResolver.setOrder(2);
		return templateResolver;
	}

	@Bean
	public TemplateResolver emailTemplateResolver() {
		TemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix("mail/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(THYMELEAF_TEMPLATE_MODE);
		templateResolver.setOrder(1);
		return templateResolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine(IMessageResolver messageResolver) {
		final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.addTemplateResolver(webTemplateResolver());
		templateEngine.addTemplateResolver(emailTemplateResolver());
		templateEngine.addDialect(new SpringSecurityDialect());
		templateEngine.addMessageResolver(messageResolver);
		return templateEngine;
	}

	@Bean
	public JavaMailSender mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(this.mailSettings.getHost());
		mailSender.setPort(this.mailSettings.getPort());
		mailSender.setPassword(this.mailSettings.getPassword());
		mailSender.setUsername(this.mailSettings.getUsername());
		mailSender.setProtocol(this.mailSettings.getProtocol());
		mailSender.setJavaMailProperties(this.mailSettings.asProperties());
		return mailSender;
	}

	@Bean
	@Scope(BeanDefinition.SCOPE_PROTOTYPE)
	public ChartColorManager chartColorManager() {
		ChartColorManager chartColorManager = new ChartColorManager();
		chartColorManager.addColor(new ChartColor("#C0392C", "#E56658"));
		chartColorManager.addColor(new ChartColor("#3498DB", "#3EA1E4"));
		chartColorManager.addColor(new ChartColor("#9B59B6", "#B268CD"));
		chartColorManager.addColor(new ChartColor("#62CB31", "#71EC3C"));
		chartColorManager.addColor(new ChartColor("#FFB606", "#FFC640"));
		chartColorManager.addColor(new ChartColor("#056932", "#1E9C59"));
		chartColorManager.addColor(new ChartColor("#E67E22", "#FC8F30"));
		chartColorManager.addColor(new ChartColor("#FFFF00", "#FFFF5B"));
		chartColorManager.addColor(new ChartColor("#E74C3C", "#FD7262"));
		chartColorManager.addColor(new ChartColor("#34495E", "#56799B"));
		return chartColorManager;
	}
}