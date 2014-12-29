package com.proeza.sgs.web.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.proeza.sgs.menu.ViewMenuManager;

@Controller
public class HomeController {

	public static final String	PAGE_CODE	= "P_HOME";
	public static final String	PAGE_NAME	= "home";

	@Autowired
	private ViewMenuManager		menuManager;

	@RequestMapping({"/", "/" + PAGE_NAME})
	public ModelAndView home (ModelAndView model, Principal principal) {
		model.setViewName(PAGE_NAME);
		model.addAllObjects(this.menuManager.getMenus(PAGE_CODE, principal));
		return model;
	}

	@ModelAttribute("pageName")
	public String pageName () {
		return PAGE_NAME;
	}
}