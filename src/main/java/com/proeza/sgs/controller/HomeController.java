package com.proeza.sgs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping({"/home"})
	public ModelAndView home (ModelAndView model) {
		model.setViewName("home");
		return model;
	}

	@ModelAttribute("pageName")
	public String pageName () {
		return "home";
	}
}