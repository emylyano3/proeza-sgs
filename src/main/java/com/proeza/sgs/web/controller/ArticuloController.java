package com.proeza.sgs.web.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proeza.sgs.business.service.IClaseService;
import com.proeza.sgs.web.menu.IViewMenuManager;

@Controller
public class ArticuloController {

	public static final String	PAGE_GROUP	= "articulo";

	@Autowired
	private IViewMenuManager	menuManager;

	@Autowired
	private IClaseService	   claseService;

	@ModelAttribute
	public void context (final ModelMap model) {
		model.addAttribute("clases", this.claseService.findAll());
	}

	@RequestMapping({"/articulo/{page}"})
	public String home (ModelMap model, Principal principal, @PathVariable("page") String page) {
		model.addAllAttributes(this.menuManager.getMenus(PAGE_GROUP, page, principal));
		return PAGE_GROUP + "/" + page + ".html";
	}
}