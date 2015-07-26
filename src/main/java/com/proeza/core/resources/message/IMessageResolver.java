package com.proeza.core.resources.message;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

public interface IMessageResolver {

    String getMessage (String code);

    String getMessage (String code, HttpServletRequest request);

    String getMessage (String code, HttpServletRequest request, String... params);

    String getMessage (String code, Locale locale);

    String getMessage (String code, Locale locale, String... params);

    String getMessage (String code, String... params);

}