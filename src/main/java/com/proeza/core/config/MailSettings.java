package com.proeza.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailSettings extends Settings {

	@Value("${mail.server.host}")
	private String	host;

	@Value("${mail.server.port}")
	private int	port;

	@Value("${mail.server.protocol}")
	private String	protocol;

	@Value("${mail.server.username}")
	private String	username;

	@Value("${mail.server.password}")
	private String	password;

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
}