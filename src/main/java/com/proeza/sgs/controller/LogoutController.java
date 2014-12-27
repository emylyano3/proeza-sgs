package com.proeza.sgs.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LogoutController {

	public static final String	PAGE_CODE	= "P_LOGOUT";
	public static final String	PAGE_NAME	= "logout";

	@Autowired
	private MessageSource		messages;

	@Autowired
	private LocaleResolver		localeResolver;

	@RequestMapping(value = "/" + PAGE_NAME, method = RequestMethod.GET)
	public ModelAndView logout (ModelAndView model, HttpServletRequest request) {
		String logoutMsg = this.messages.getMessage("sec.logoutsuccess", null, this.localeResolver.resolveLocale(request));
		model.addObject("logoutMsg", logoutMsg);
		model.setViewName(PAGE_NAME);
		return model;
	}
}