package com.proeza.sgs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.proeza.sgs.menu.ViewMenuManager;

@Controller
public class HomeController {

	public static final String	PAGE_CODE	= "P_HOME";

	@Autowired
	private ViewMenuManager			menuManager;

	@RequestMapping({"/", "/home"})
	public ModelAndView home (ModelAndView model) {
		model.setViewName("home");
		model.addObject("menu", this.menuManager.getMenu(PAGE_CODE));
		return model;
	}

	@ModelAttribute("pageName")
	public String pageName () {
		return "home";
	}
}