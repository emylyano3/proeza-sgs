package com.proeza.security.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proeza.core.util.date.DateUtil;
import com.proeza.security.dao.ILoginDao;
import com.proeza.security.dao.IUsuarioDao;
import com.proeza.security.entity.Login;
import com.proeza.security.entity.Usuario;

@Service
@Transactional
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private static final Logger log = Logger.getLogger(LoginSuccessHandler.class);

    @Autowired
    private ILoginDao           loginDao;

    @Autowired
    private IUsuarioDao         usuarioDao;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        User user = (User) authentication.getPrincipal();
        log.info("User logged: " + user);
        try {
            Usuario systemUser = this.usuarioDao.findByAlias(user.getUsername());
            Login login = new Login();
            login.setUser(systemUser);
            login.setFecha(DateUtil.createNow());
            this.loginDao.persist(login);
        } catch (Exception e) {
            log.error("Error persistiendo el login del usuario [" + user + "]: " + e.getMessage());
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}