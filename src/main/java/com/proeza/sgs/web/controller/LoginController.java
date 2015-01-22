package com.proeza.sgs.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.proeza.core.resources.IMessageResolver;

@Controller
public class LoginController {

	public static final String	PAGE_GROUP	= "root";
	public static final String	PAGE_NAME	= "login";

	@Autowired
	private IMessageResolver		messageResolver;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login (
		ModelAndView model,
		@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout,
		HttpServletRequest request)
	{
		model.setViewName(PAGE_GROUP + "/" + PAGE_NAME + ".html");
		if (error != null) {
			model.addObject("errorMsg", getLoginErrorMessage(request));
		}
		if (logout != null) {
			String logoutMsg = this.messageResolver.getMessage("sec.logoutsuccess", request);
			model.addObject("logoutMsg", logoutMsg);
		}
		return model;
	}

	private String getLoginErrorMessage (HttpServletRequest request) {
		final Exception exception = (Exception) request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		if (exception instanceof BadCredentialsException) {
			return this.messageResolver.getMessage("sec.badcredentials", request);
		} else if (exception instanceof LockedException) {
			return this.messageResolver.getMessage("sec.accountlocked", request);
		} else {
			return this.messageResolver.getMessage("sec.loginerror", request);
		}
	}
}