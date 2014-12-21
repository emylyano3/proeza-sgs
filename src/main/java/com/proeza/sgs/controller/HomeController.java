package com.proeza.sgs.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping({"/", "/home"})
	public ModelAndView home (ModelAndView model) {
		model.setViewName("home");
		List<String> breadcumb = Arrays.asList(new String[]{"home","dashboard"});
		model.addObject("breadcumb", breadcumb);
		return model;
	}

	@ModelAttribute("pageName")
	public String pageName () {
		return "home";
	}
}