package com.proeza.sgs.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	public static final String	PAGE_CODE	= "P_LOGIN";
	public static final String	PAGE_NAME	= "login";

	@Autowired
	private MessageSource		messages;

	@Autowired
	private LocaleResolver		localeResolver;

	@RequestMapping(value = "/" + PAGE_NAME, method = RequestMethod.GET)
	public ModelAndView login (
		ModelAndView model,
		@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout,
		HttpServletRequest request)
	{
		model.setViewName(PAGE_NAME);
		if (error != null) {
			model.addObject("errorMsg", getLoginErrorMessage(request));
		}
		if (logout != null) {
			String logoutMsg = this.messages.getMessage("sec.logoutsuccess", null, this.localeResolver.resolveLocale(request));
			model.addObject("logoutMsg", logoutMsg);
		}
		return model;
	}

	private String getLoginErrorMessage (HttpServletRequest request) {
		final Exception exception = (Exception) request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		if (exception instanceof BadCredentialsException) {
			return this.messages.getMessage("sec.badcredentials", null, this.localeResolver.resolveLocale(request));
		} else if (exception instanceof LockedException) {
			return this.messages.getMessage("sec.accountlocked", null, this.localeResolver.resolveLocale(request));
		} else {
			return this.messages.getMessage("sec.loginerror", null, this.localeResolver.resolveLocale(request));
		}
	}
}