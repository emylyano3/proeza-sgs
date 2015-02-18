package com.proeza.sgs.web.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proeza.sgs.web.menu.IViewMenuManager;

@Controller
public class UsuarioController {

	public static final String	PAGE_GROUP	= "usuario";

	@Autowired
	private IViewMenuManager		menuManager;

	@RequestMapping({"/usuario/{page}"})
	public String home (ModelMap model, Principal principal, @PathVariable("page") String pageName) {
		model.addAllAttributes(this.menuManager.getMenus(PAGE_GROUP, pageName, principal));
		return PAGE_GROUP + "/" + pageName + ".html";
	}
}