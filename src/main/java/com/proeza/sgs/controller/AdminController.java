package com.proeza.sgs.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.proeza.security.dao.UsuarioDao;
import com.proeza.security.entity.Usuario;

@Controller
public class AdminController {

	@Autowired
	private UsuarioDao	usuarioDao;

	@RequestMapping({"/admin"})
	public ModelAndView home (ModelAndView model, Principal principal) {
		User activeUser = (User) ((Authentication) principal).getPrincipal();
		Usuario usuario = this.usuarioDao.findByAlias(activeUser.getUsername());
		model.addObject("usuario", usuario);
		model.setViewName("admin");
		return model;
	}

	@ModelAttribute("pageName")
	public String pageName () {
		return "admin";
	}
}