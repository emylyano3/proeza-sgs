package com.proeza.sgs.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proeza.sgs.system.service.IMenuService;
import com.proeza.sgs.system.service.dto.MenuDTO;

@RestController
@RequestMapping("rest/application")
@CrossOrigin()
public class AppManagementRestController {

	@Autowired
	private IMenuService menuService;

	@RequestMapping(value = "/menu/{code}/{user}", method = RequestMethod.GET)
	public MenuDTO getMenu(@PathVariable String code, @PathVariable String user) {
		return this.menuService.getMenu(code, user);
	}

}