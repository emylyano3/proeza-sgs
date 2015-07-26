package com.proeza.core.resources.message;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.web.servlet.LocaleResolver;

public class MessageResolver implements IMessageResolver {

    private MessageSource  messageSource;
    private LocaleResolver localeResolver;

    public MessageResolver (MessageSource messageSource, LocaleResolver localeResolver) {
        this.messageSource = messageSource;
        this.localeResolver = localeResolver;
    }

    @Override
    public String getMessage (String code) {
        return this.messageSource.getMessage(code, null, null);
    }

    @Override
    public String getMessage (String code, HttpServletRequest request) {
        return this.messageSource.getMessage(code, null, this.localeResolver.resolveLocale(request));
    }

    @Override
    public String getMessage (String code, HttpServletRequest request, String... params) {
        return this.messageSource.getMessage(code, params, this.localeResolver.resolveLocale(request));
    }

    @Override
    public String getMessage (String code, Locale locale) {
        return this.messageSource.getMessage(code, null, locale);
    }

    @Override
    public String getMessage (String code, Locale locale, String... params) {
        return this.messageSource.getMessage(code, params, locale);
    }

    @Override
    public String getMessage (String code, String... params) {
        return this.messageSource.getMessage(code, params, null);
    }
}