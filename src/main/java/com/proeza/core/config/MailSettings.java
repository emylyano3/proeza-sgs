package com.proeza.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailSettings extends Settings {

	@Value("${mail.server.host}")
	private String	host;

	@Value("${mail.server.port}")
	private int		port;

	@Value("${mail.server.protocol}")
	private String	protocol;

	@Value("${mail.server.username}")
	private String	username;

	@Value("${mail.server.password}")
	private String	password;

	@Value("${mail.sender.defaultEncoding}")
	private String	defaultEncoding;

	@Value("${mail.sender.smtp.starttls.enable}")
	private String	smtpStarttlsEnabled;

	@Value("${mail.sender.smtp.socketFactory.fallback}")
	private String	smtpSocketFactoryFallback;

	@Value("${mail.sender.smtp.socketFactory.class}")
	private String	smtpSocketFactoryClass;

	@Value("${mail.sender.smtp.socketFactory.port}")
	private int		smtpSocketFactoryPort;

	@Value("${mail.sender.smtp.auth}")
	private String	smtpAuth;

	public String getHost () {
		return this.host;
	}

	public int getPort () {
		return this.port;
	}

	public String getProtocol () {
		return this.protocol;
	}

	public String getUsername () {
		return this.username;
	}

	public String getPassword () {
		return this.password;
	}

	public String getDefaultEncoding () {
		return this.defaultEncoding;
	}

	public String getSmtpStarttlsEnabled () {
		return this.smtpStarttlsEnabled;
	}

	public String getSmtpSocketFactoryFallback () {
		return this.smtpSocketFactoryFallback;
	}

	public String getSmtpSocketFactoryClass () {
		return this.smtpSocketFactoryClass;
	}

	public int getSmtpSocketFactoryPort () {
		return this.smtpSocketFactoryPort;
	}

	public String getSmtpAuth () {
		return this.smtpAuth;
	}
}