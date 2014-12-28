package com.proeza.sgs.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.proeza.security.entity.Usuario;
import com.proeza.security.form.UsuarioForm;
import com.proeza.sgs.menu.ViewMenuManager;

@Controller
public class RegisterController {

	public static final String	PAGE_CODE	= "P_REGISTER";
	public static final String	PAGE_NAME	= "register";

	@Autowired
	private ViewMenuManager		menuManager;

	@RequestMapping(value = "/" + PAGE_NAME, method = RequestMethod.GET)
	public ModelAndView getForm (ModelAndView model, Principal principal) {
		model.setViewName(PAGE_NAME);
		Usuario user = new Usuario();
		user.setNombre("Pepe");
		model.addObject("user", user);
		model.addAllObjects(this.menuManager.getMenus(PAGE_CODE, principal));
		return model;
	}

	@RequestMapping(value = "/" + PAGE_NAME, method = RequestMethod.POST)
	public ModelAndView register (ModelAndView model, @Valid UsuarioForm userForm) {
		model.setViewName("redirect:" + HomeController.PAGE_NAME);
		model.addObject("user", userForm);
		return model;
	}
}