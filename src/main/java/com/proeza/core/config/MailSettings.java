package com.proeza.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailSettings extends Settings {

    public MailSettings () {
    }

    @Value("${mail.smtp.host}")
    private String  host;

    @Value("${mail.smtp.port}")
    private Integer port;

    @Value("${mail.smtp.protocol}")
    private String  protocol;

    @Value("${mail.smtp.username}")
    private String  username;

    @Value("${mail.smtp.password}")
    private String  password;

    @Value("${mail.smtp.defaultEncoding}")
    private String  defaultEncoding;

    @Value("${mail.smtp.starttls.enable}")
    private String  smtpStarttlsEnabled;

    @Value("${mail.smtp.socketFactory.fallback}")
    private String  smtpSocketFactoryFallback;

    @Value("${mail.smtp.socketFactory.class}")
    private String  smtpSocketFactoryClass;

    @Value("${mail.smtp.socketFactory.port}")
    private Integer smtpSocketFactoryPort;

    @Value("${mail.smtp.auth}")
    private String  smtpAuth;

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