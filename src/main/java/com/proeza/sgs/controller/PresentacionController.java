package com.proeza.sgs.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PresentacionController {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager	entityManager;

	@RequestMapping({"/", "/presentacion"})
	public ModelAndView home (ModelAndView model) {
		model.setViewName("presentacion");
		return model;
	}

	@ModelAttribute("pageName")
	public String pageName () {
		return "presentacion";
	}
}