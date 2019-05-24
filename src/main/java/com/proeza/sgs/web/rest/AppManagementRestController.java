package com.proeza.sgs.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proeza.sgs.system.service.IMenuService;
import com.proeza.sgs.system.service.dto.MenuDTO;

@RestController
@RequestMapping("rest/application")
public class AppManagementRestController {
//	@Autowired
//	private TaskExecutor taskExecutor;
//
//	public void executeAsynchronously() {
//		this.taskExecutor.execute(() -> {
//			// TODO add long running task
//		});
//	}

	@Autowired
	private IMenuService menuService;

	@RequestMapping(value = "getMenu", method = RequestMethod.POST)
	public List<MenuDTO> getMenus() {
		return this.menuService.getMenus();
	}

	@RequestMapping(value = "getMenu/{code}", method = RequestMethod.POST)
	public MenuDTO getMenu(@PathVariable String code) {
		return this.menuService.getMenu(code);
	}

}