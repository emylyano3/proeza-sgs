package com.proeza.sgs.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.proeza.core.resources.IMessageResolver;

@Controller
public class LogoutController {

    public static final String PAGE_GROUP = "root";
    public static final String PAGE_NAME  = "logout";

    @Autowired
    private IMessageResolver   messageResolver;

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout (ModelAndView model, HttpServletRequest request) {
        String logoutMsg = this.messageResolver.getMessage("sec.logoutsuccess", request);
        model.addObject("logoutMsg", logoutMsg);
        model.setViewName(PAGE_GROUP + "/" + PAGE_NAME + ".html");
        return model;
    }
}