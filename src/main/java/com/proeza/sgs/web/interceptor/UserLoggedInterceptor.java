package com.proeza.sgs.web.interceptor;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.proeza.security.dto.UsuarioDTO;
import com.proeza.security.service.IUserService;

public class UserLoggedInterceptor implements HandlerInterceptor {

    @Autowired
    private IUserService userService;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView model) throws Exception {
        Principal principal;
        /*
         * Chequeo si el modelo existe en caso de que el post handle venga de un flujo que no maneje modelo para renderizar. Por ejemplo:
         * Una invocacion a un servicio REST
         */
        if (model != null && (principal = request.getUserPrincipal()) != null) {
            UsuarioDTO user = this.userService.findByAlias(principal.getName());
            model.addObject("user", user);
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }
}