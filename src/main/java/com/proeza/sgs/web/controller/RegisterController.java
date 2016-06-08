package com.proeza.sgs.web.controller;

import java.security.Principal;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;

import com.proeza.core.classmapper.Mapeable;
import com.proeza.security.form.UsuarioForm;
import com.proeza.security.service.IUserService;
import com.proeza.sgs.system.mail.IMailManager;
import com.proeza.sgs.system.service.IPageService;
import com.proeza.sgs.system.service.IMenuService;
import com.proeza.sgs.web.PageConfig;

@Controller
public class RegisterController {

    public static final String PAGE_NAME  = "register";
    public static final String PAGE_GROUP = "root";

    @Autowired
    private IUserService       userService;

    @Autowired
    private IMenuService       menuService;

    @Autowired
    private IMailManager       mailManager;

    @Autowired
    private LocaleResolver     localeResolver;

    @Autowired
    private IPageService       pageService;

    @ModelAttribute
    public void menues(final ModelMap model, final Principal principal) {
        model.addAllAttributes(this.menuService.getMenus(PAGE_GROUP, PAGE_NAME, principal));
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getForm(final ModelMap model, final UsuarioForm usuarioForm) {
        model.addAttribute("pageConfig", buildPageConfig(PAGE_GROUP, PAGE_NAME));
        usuarioForm.setAlias("Emy");
        usuarioForm.setNombre("Emiliano");
        usuarioForm.setApellido("Schiano");
        usuarioForm.setEmail("emylyano3@gmail.com");
        usuarioForm.setPassword("aaaaa");
        usuarioForm.setPasswordConfirm("aaaaa");
        model.addAttribute("userForm", usuarioForm);
        return PAGE_GROUP + "/" + PAGE_NAME + ".html";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(final ModelMap model, @ModelAttribute("userForm") @Valid UsuarioForm userForm, final BindingResult result, HttpServletRequest request) throws MessagingException {
        if (result.hasErrors()) {
            return PAGE_GROUP + "/" + PAGE_NAME + ".html";
        } else {
            userForm = this.userService.create(userForm);
            this.mailManager.sendRegisterEmail(userForm.getUsuario(), this.localeResolver.resolveLocale(request));
            return "redirect:/" + HomeController.PAGE_NAME;
        }
    }

    private Mapeable buildPageConfig(String group, String page) {
        return new PageConfig().mapFrom(this.pageService.findByGroupAndName(group, page));
    }
}