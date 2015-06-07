package com.proeza.core.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService implements IMailService {

    private static final Logger log = Logger.getLogger(MailService.class);

    @Autowired
    private JavaMailSender      mailSender;

    @Override
    public void send (MimeMessage mimeMessage) throws MessagingException {
        try {
            this.mailSender.send(mimeMessage);
        } catch (Exception e) {
            log.error("Error enviando el correo: " + mimeMessage, e);
        }
    }
}