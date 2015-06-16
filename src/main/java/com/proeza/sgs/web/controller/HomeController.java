package com.proeza.sgs.web.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.proeza.sgs.system.dao.IPageDao;
import com.proeza.sgs.system.entity.Page;
import com.proeza.sgs.web.menu.IViewMenuManager;

@Controller
public class HomeController {

    public static final String PAGE_GROUP = "root";
    public static final String PAGE_NAME  = "home";

    @Autowired
    private IViewMenuManager   menuManager;

    @Autowired
    private IPageDao           pageDao;

    @ModelAttribute
    public void menues (final ModelMap model, final Principal principal) {
        model.addAllAttributes(this.menuManager.getMenus(PAGE_GROUP, PAGE_NAME, principal));
    }

    @RequestMapping({"/", "/index", "/home"})
    public ModelAndView home (ModelAndView model) {
        model.setViewName(PAGE_GROUP + "/" + PAGE_NAME + ".html");
        Page pagina = this.pageDao.findByGroupAndName(PAGE_GROUP, PAGE_NAME);
        model.addObject("pageTitle", pagina.getTitle());
        model.addObject("pageSubtitle", pagina.getSubtitle());
        return model;
    }
}