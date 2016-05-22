package com.proeza.sgs.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.proeza.core.resources.message.IMessageResolver;

@Controller
public class LogoutController {

    public static final String PAGE_GROUP = "root";
    public static final String PAGE_NAME  = "logout";

    @Autowired
    private IMessageResolver   messageResolver;

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(ModelMap model, HttpServletRequest request) {
        String logoutMsg = this.messageResolver.getMessage("sec.logoutsuccess", request);
        model.addAttribute("logoutMsg", logoutMsg);
        return PAGE_GROUP + "/" + PAGE_NAME + ".html";
    }
}