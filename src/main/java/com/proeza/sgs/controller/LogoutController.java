package com.proeza.sgs.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.proeza.core.MessageResolver;

@Controller
public class LogoutController {

	public static final String	PAGE_CODE	= "P_LOGOUT";
	public static final String	PAGE_NAME	= "logout";

	@Autowired
	private MessageResolver		messageResolver;

	@RequestMapping(value = "/" + PAGE_NAME, method = RequestMethod.GET)
	public ModelAndView logout (ModelAndView model, HttpServletRequest request) {
		String logoutMsg = this.messageResolver.getMessage("sec.logoutsuccess", request);
		model.addObject("logoutMsg", logoutMsg);
		model.setViewName(PAGE_NAME);
		return model;
	}
}