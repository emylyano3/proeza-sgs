package com.proeza.sgs.web.rest;

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

    @Autowired
    private IMenuService menuService;

    @RequestMapping(value = "getMenu/{code}", method = RequestMethod.POST)
    public MenuDTO getMenu(@PathVariable String code) {
        return this.menuService.getMenu(code);
    }

}