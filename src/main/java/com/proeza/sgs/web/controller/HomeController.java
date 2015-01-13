package com.proeza.sgs.web.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.proeza.sgs.web.menu.ViewMenuManager;

@Controller
public class HomeController {

	public static final String	PAGE_NAME	= "home";

	@Autowired
	private ViewMenuManager		menuManager;

	@ModelAttribute
	public void menues (final ModelMap model, final Principal principal) {
		model.addAllAttributes(this.menuManager.getMenus(PAGE_NAME, principal));
	}

	@RequestMapping({"/", "/" + PAGE_NAME})
	public ModelAndView home (ModelAndView model, Principal principal) {
		model.setViewName(PAGE_NAME);
		return model;
	}
}