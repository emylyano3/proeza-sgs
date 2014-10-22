package com.proeza.sgs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

	@RequestMapping({"/admin"})
	public ModelAndView home (ModelAndView model) {
		model.setViewName("admin");
		return model;
	}

	@ModelAttribute("pageName")
	public String pageName () {
		return "admin";
	}
}