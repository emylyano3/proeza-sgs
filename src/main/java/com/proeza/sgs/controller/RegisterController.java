package com.proeza.sgs.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.proeza.security.form.UsuarioForm;
import com.proeza.sgs.menu.ViewMenuManager;

@Controller
public class RegisterController {

	public static final String	PAGE_CODE	= "P_REGISTER";
	public static final String	PAGE_NAME	= "register";

	@Autowired
	private ViewMenuManager		menuManager;

	@ModelAttribute
	public void menues (final ModelMap model, final Principal principal) {
		model.addAllAttributes(this.menuManager.getMenus(PAGE_CODE, principal));
	}

	@RequestMapping(value = "/" + PAGE_NAME, method = RequestMethod.GET)
	public String getForm (final ModelMap model, final UsuarioForm usuarioForm) {
		model.addAttribute("userForm", usuarioForm);
		return PAGE_NAME;
	}

	@RequestMapping(value = "/" + PAGE_NAME, params = {"save"}, method = RequestMethod.POST)
	public String register (final ModelMap model, @Valid final UsuarioForm userForm, final BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("userForm", userForm);
			return PAGE_NAME;
		} else {
			return "redirect:/" + HomeController.PAGE_NAME;
		}
	}
}