package com.proeza.sgs.system.mail;

import java.util.Locale;

import javax.mail.MessagingException;

import com.proeza.security.entity.Usuario;

public interface IMailManager {

    void sendRegisterEmail (Usuario user, Locale locale) throws MessagingException;
}