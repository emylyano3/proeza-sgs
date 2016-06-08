package com.proeza.sgs.web.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proeza.core.classmapper.Mapeable;
import com.proeza.security.service.IRoleService;
import com.proeza.sgs.system.service.IPageService;
import com.proeza.sgs.system.service.IMenuService;
import com.proeza.sgs.web.PageConfig;

@Controller
public class UsuarioController {

    public static final String PAGE_GROUP = "usuario";

    @Autowired
    private IMenuService       menuService;

    @Autowired
    private IPageService       pageService;

    @Autowired
    private IRoleService       rolService;

    @ModelAttribute
    public void context(final ModelMap model) {
        model.addAttribute("roles", this.rolService.findAll());
    }

    @RequestMapping({"/usuario/{page}"})
    public String home(ModelMap model, Principal principal, @PathVariable("page") String pageName) {
        model.addAllAttributes(this.menuService.getMenus(PAGE_GROUP, pageName, principal));
        model.addAttribute("pageConfig", buildPageConfig(PAGE_GROUP, pageName));
        return PAGE_GROUP + "/" + pageName + ".html";
    }

    private Mapeable buildPageConfig(String group, String page) {
        return new PageConfig().mapFrom(this.pageService.findByGroupAndName(group, page));
    }
}