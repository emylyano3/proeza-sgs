package proeza.test.integration.mail;

import nz.net.ultraq.thymeleaf.LayoutDialect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.extras.springsecurity3.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import com.proeza.core.config.MailSettings;
import com.proeza.core.config.Settings;
import com.proeza.core.service.MailService;
import com.proeza.sgs.system.mail.MailManager;

@Configuration
@ComponentScan(basePackageClasses = {Settings.class, MailService.class, MailManager.class})
public class MailServiceTestContext {

	@Autowired
	private MailSettings	mailSettings;

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

	@Bean
	public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer () {
		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
		ppc.setLocation(new ClassPathResource("env/dev/app-config.properties"));
		ppc.setNullValue("");
		return ppc;
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
	public SpringTemplateEngine templateEngine () {
		final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.addTemplateResolver(emailTemplateResolver());
		templateEngine.addDialect(new LayoutDialect());
		templateEngine.addDialect(new SpringSecurityDialect());
		return templateEngine;
	}
}