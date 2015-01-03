package com.proeza.sgs.web.controller;

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
import com.proeza.security.service.UserService;
import com.proeza.sgs.web.menu.ViewMenuManager;

@Controller
public class RegisterController {

	public static final String	PAGE_CODE	= "P_REGISTER";
	public static final String	PAGE_NAME	= "register";

	@Autowired
	private UserService			userService;

	@Autowired
	private ViewMenuManager		menuManager;

	@ModelAttribute
	public void menues (final ModelMap model, final Principal principal) {
		model.addAllAttributes(this.menuManager.getMenus(PAGE_CODE, principal));
	}

	@RequestMapping(value = "/" + PAGE_NAME, method = RequestMethod.GET)
	public String getForm (final ModelMap model, final UsuarioForm usuarioForm) {
		usuarioForm.setAlias("Emy");
		usuarioForm.setNombre("Emiliano");
		usuarioForm.setApellido("Schiano");
		usuarioForm.setEmail("emylyano3@gmail.com");
		usuarioForm.setPassword("aaaaa");
		usuarioForm.setPasswordConfirm("aaaaa");
		model.addAttribute("userForm", usuarioForm);
		return PAGE_NAME;
	}

	@RequestMapping(value = "/" + PAGE_NAME, params = {"save"}, method = RequestMethod.POST)
	public String register (final ModelMap model, @ModelAttribute("userForm") @Valid final UsuarioForm userForm, final BindingResult result) {
		if (result.hasErrors()) {
			return PAGE_NAME;
		} else {
			this.userService.create(userForm.getUsuario());
			return "redirect:/" + HomeController.PAGE_NAME;
		}
	}
	//
	//	@ResponseStatus(value = HttpStatus.CONFLICT, reason = "El usuario ya existe")
	//	@ExceptionHandler(PersistenceException.class)
	//	public void handler (PersistenceException e) {
	//
	//	}
}