package com.proeza.sgs.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
		model.addObject("userForm", new UsuarioForm());
		model.addAllObjects(this.menuManager.getMenus(PAGE_CODE, principal));
		return model;
	}

	@RequestMapping(value = "/" + PAGE_NAME, method = RequestMethod.POST)
	public ModelAndView register (ModelAndView model, Principal principal, @Valid UsuarioForm userForm, BindingResult result) {
		if (result.hasErrors()) {
			model.addAllObjects(this.menuManager.getMenus(PAGE_CODE, principal));
			model.addObject("userForm", userForm);
			model.setViewName(PAGE_NAME);
		} else {
			model.addObject("userForm", userForm);
			model.setViewName("redirect:" + HomeController.PAGE_NAME);
		}
		return model;
	}
}