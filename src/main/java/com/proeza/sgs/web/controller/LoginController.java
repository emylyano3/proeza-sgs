package com.proeza.sgs.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.proeza.core.resources.message.IMessageResolver;

@Controller
public class LoginController {

    private static final String SEC_ACCOUNTLOCKED  = "sec.accountlocked";
    private static final String SEC_BADCREDENTIALS = "sec.badcredentials";
    private static final String SEC_LOGOUTSUCCESS  = "sec.logoutsuccess";

    public static final String  PAGE_GROUP         = "root";
    public static final String  PAGE_NAME          = "login";

    @Autowired
    private IMessageResolver    messageResolver;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model, @RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {
        if (error != null) {
            model.addAttribute("errorMsg", getLoginErrorMessage(request));
        }
        if (logout != null) {
            String logoutMsg = this.messageResolver.getMessage(SEC_LOGOUTSUCCESS, request);
            model.addAttribute("logoutMsg", logoutMsg);
        }
        return PAGE_GROUP + "/" + PAGE_NAME + ".html";
    }

    private String getLoginErrorMessage(HttpServletRequest request) {
        final Exception exception = (Exception) request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        if (exception instanceof BadCredentialsException) {
            return this.messageResolver.getMessage(SEC_BADCREDENTIALS, request);
        } else if (exception instanceof LockedException) {
            return this.messageResolver.getMessage(SEC_ACCOUNTLOCKED, request);
        } else {
            return this.messageResolver.getMessage("sec.loginerror", request);
        }
    }
}