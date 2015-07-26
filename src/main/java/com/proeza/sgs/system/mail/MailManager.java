package com.proeza.sgs.system.mail;

import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.proeza.core.resources.message.IMessageResolver;
import com.proeza.core.service.IMailService;
import com.proeza.core.service.MailService;
import com.proeza.security.entity.Usuario;

@Component
public class MailManager implements IMailManager {

    private static final String TEMPLATE_REGISTER = "mail-register";

    private static final String ENCODING          = "UTF-8";

    private static final Logger log               = Logger.getLogger(MailService.class);

    @Autowired
    private JavaMailSender      mailSender;

    @Autowired
    private TemplateEngine      templateEngine;

    @Autowired
    private IMailService        mailService;

    @Autowired
    private IMessageResolver    messageResolver;

    @Value("${app.emailing.remittent}")
    private String              remittent;

    @Override
    @Async
    public void sendRegisterEmail (Usuario user, final Locale locale) throws MessagingException {
        // Prepare the evaluation context
        final Context ctx = new Context(locale);
        ctx.setVariable("username", user.getNombre());
        ctx.setVariable("alias", user.getAlias());

        // Prepare message using a Spring helper
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, false, ENCODING);
        message.setSubject(this.messageResolver.getMessage("mail.template.subject", locale));
        message.setFrom(this.remittent);
        message.setTo(user.getEmail());

        // Create the HTML body using Thymeleaf
        final String htmlContent = this.templateEngine.process(TEMPLATE_REGISTER, ctx);
        message.setText(htmlContent, true);

        try {
            this.mailService.send(mimeMessage);
        } catch (Exception e) {
            log.error("Error enviando el correo: " + mimeMessage, e);
        }
    }
}