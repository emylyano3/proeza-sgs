package com.proeza.sgs.web.rest;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proeza.sgs.config.root.ContextLocale;
import com.proeza.sgs.system.service.IMenuService;
import com.proeza.sgs.system.service.dto.MenuDTO;

@CrossOrigin
@RestController
@RequestMapping("rest/application")
public class AppManagementRestController {

	@Autowired
	private IMenuService	menuService;

	@Autowired
	private ContextLocale	contextLocale;

	@RequestMapping(value = "/menu/{code}/{user}", method = RequestMethod.GET)
	public MenuDTO getMenu (@PathVariable String code, @PathVariable String user, Locale locale) {
		this.contextLocale.setLocale(locale.toString());
		return this.menuService.getMenu(code, user, locale.toString());
	}

}