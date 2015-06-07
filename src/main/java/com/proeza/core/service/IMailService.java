package com.proeza.core.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public interface IMailService {

    void send (MimeMessage mimeMessage) throws MessagingException;
}