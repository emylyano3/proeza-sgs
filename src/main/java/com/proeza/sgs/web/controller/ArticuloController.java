package com.proeza.sgs.web.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proeza.core.classmapper.Mapeable;
import com.proeza.sgs.business.service.IClaseService;
import com.proeza.sgs.business.service.IMarcaService;
import com.proeza.sgs.business.service.IRubroService;
import com.proeza.sgs.business.service.ITipoService;
import com.proeza.sgs.system.service.IPageService;
import com.proeza.sgs.system.service.IMenuService;
import com.proeza.sgs.web.PageConfig;

@Controller
@RequestMapping({"/articulo"})
public class ArticuloController {

    public static final String PAGE_GROUP = "articulo";

    @Autowired
    private IMenuService       menuService;

    @Autowired
    private IClaseService      claseService;

    @Autowired
    private ITipoService       tipoService;

    @Autowired
    private IMarcaService      marcaService;

    @Autowired
    private IRubroService      rubroService;

    @Autowired
    private IPageService       pageService;

    @ModelAttribute
    public void context(final ModelMap model) {
        model.addAttribute("clases", this.claseService.findAll());
        model.addAttribute("tipos", this.tipoService.findAll());
        model.addAttribute("marcas", this.marcaService.findAll());
        model.addAttribute("rubros", this.rubroService.findAll());
    }

    @RequestMapping({"/{page}"})
    public String home(ModelMap model, Principal principal, @PathVariable("page") String page) {
        model.addAllAttributes(this.menuService.getMenus(PAGE_GROUP, page, principal));
        model.addAttribute("pageConfig", buildPageConfig(PAGE_GROUP, page));
        return PAGE_GROUP + "/" + page + ".html";
    }

    private Mapeable buildPageConfig(String group, String page) {
        PageConfig config = new PageConfig().mapFrom(this.pageService.findByGroupAndName(group, page));
        config.setHasSearch(!"stats".equals(page));
        return config;
    }
}