package com.proeza.sgs.web.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.proeza.security.dao.UsuarioDao;
import com.proeza.sgs.web.menu.ViewMenuManager;

@Controller
public class AdminController {

	public static final String	PAGE_CODE	= "P_ADMIN";
	public static final String	PAGE_NAME	= "admin";

	@Autowired
	private UsuarioDao			usuarioDao;

	@Autowired
	private ViewMenuManager		menuManager;

	@RequestMapping({"/" + PAGE_NAME})
	public ModelAndView home (ModelAndView model, Principal principal) {
		//		User activeUser = (User) ((Authentication) principal).getPrincipal();
		//		model.addAllObjects(this.menuManager.getMenus(PAGE_CODE, activeUser.getUsername()));
		model.addAllObjects(this.menuManager.getMenus(PAGE_CODE, principal));
		model.setViewName("admin");
		return model;
	}

	@ModelAttribute("pageName")
	public String pageName () {
		return PAGE_NAME;
	}
}