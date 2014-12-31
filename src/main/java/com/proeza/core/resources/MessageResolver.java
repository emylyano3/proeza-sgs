package com.proeza.core.resources;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.web.servlet.LocaleResolver;

public class MessageResolver {

	private MessageSource	messageSource;
	private LocaleResolver	localeResolver;

	public MessageResolver (MessageSource messageSource, LocaleResolver localeResolver) {
		this.messageSource = messageSource;
		this.localeResolver = localeResolver;
	}

	public String getMessage (String code) {
		return this.messageSource.getMessage(code, null, null);
	}

	public String getMessage (String code, HttpServletRequest request) {
		return this.messageSource.getMessage(code, null, this.localeResolver.resolveLocale(request));
	}

	public String getMessage (String code, HttpServletRequest request, String... params) {
		return this.messageSource.getMessage(code, params, this.localeResolver.resolveLocale(request));
	}

	public String getMessage (String code, String... params) {
		return this.messageSource.getMessage(code, params, null);
	}
}