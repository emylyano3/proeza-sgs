package com.proeza.sgs.web.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proeza.sgs.business.service.IClaseService;
import com.proeza.sgs.business.service.IMarcaService;
import com.proeza.sgs.business.service.IRubroService;
import com.proeza.sgs.business.service.ITipoService;
import com.proeza.sgs.system.service.IPageService;
import com.proeza.sgs.system.service.dto.PageDTO;
import com.proeza.sgs.web.menu.IViewMenuManager;

@Controller
@RequestMapping({"/articulo"})
public class ArticuloController {

    public static final String PAGE_GROUP = "articulo";

    @Autowired
    private IViewMenuManager   menuManager;

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
    public void context (final ModelMap model) {
//        model.addAttribute("clases", this.claseService.findAll());
//        model.addAttribute("tipos", this.tipoService.findAll());
        model.addAttribute("marcas", this.marcaService.findAll());
        model.addAttribute("rubros", this.rubroService.findAll());
    }

    @RequestMapping({"/{page}"})
    public String home (ModelMap model, Principal principal, @PathVariable("page") String page) {
        model.addAllAttributes(this.menuManager.getMenus(PAGE_GROUP, page, principal));
        PageDTO pagina = this.pageService.findByGroupAndName(PAGE_GROUP, page);
        model.addAttribute("pageTitle", pagina.getTitle());
        model.addAttribute("pageSubtitle", pagina.getSubtitle());
        return PAGE_GROUP + "/" + page + ".html";
    }
}