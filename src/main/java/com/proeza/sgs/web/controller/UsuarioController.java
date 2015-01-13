package com.proeza.sgs.web.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.proeza.security.service.IUserService;
import com.proeza.sgs.web.menu.ViewMenuManager;

@Controller
public class UsuarioController {

	public static final String	PAGE_NAME	= "usuario";

	@Autowired
	private IUserService		usuarioService;

	@Autowired
	private ViewMenuManager		menuManager;

	@ModelAttribute
	public void menues (final ModelMap model, final Principal principal) {
		model.addAllAttributes(this.menuManager.getMenus(PAGE_NAME, principal));
	}

	@ModelAttribute("pageName")
	public String pageName () {
		return PAGE_NAME;
	}

	@RequestMapping({"/" + PAGE_NAME})
	public ModelAndView home (ModelAndView model, Principal principal) {
		return model;
	}
}