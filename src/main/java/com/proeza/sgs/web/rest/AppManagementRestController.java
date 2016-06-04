package com.proeza.sgs.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proeza.sgs.web.menu.IViewMenuManager;
import com.proeza.sgs.web.menu.ViewMenu;

@RestController
@RequestMapping("rest/application")
public class AppManagementRestController {

    @Autowired
    private IViewMenuManager menuManager;

    @RequestMapping(value = "getMenu/{code}", method = RequestMethod.POST)
    public ViewMenu getMenu(@PathVariable String code) {
        return this.menuManager.getMenu(code);
    }

}