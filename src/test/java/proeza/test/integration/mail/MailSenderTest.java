package proeza.test.integration.mail;

import java.io.IOException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailSenderTest {

    private JavaMailSenderImpl  mailSender;
    private static Properties   properties;

    private static final String APPLICATION_PROPERTIES = "/env/dev/app-config.properties";

    @BeforeClass
    public static void before () {
        ClassPathResource cpr = new ClassPathResource(APPLICATION_PROPERTIES);
        properties = new Properties();
        try {
            properties.load(cpr.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException("Error iniciando la aplicacion. No se puede cargar el archivo " + APPLICATION_PROPERTIES, e);
        }
    }

    private static String FROM_ADDRESS = "emylyano3@gmail.com";
    private static String TO_ADDRESS   = "emylyano3@gmail.com";
    private static String SUBJECT      = "Mail testing from Proeza SGS";
    private static String CONTENT      = "Contenido del Mail enviado por Proeza SGS";

    @Before
    public void setup () {
        this.mailSender = new JavaMailSenderImpl();
        this.mailSender.setUsername(properties.getProperty("mail.smtp.username"));
        this.mailSender.setPassword(properties.getProperty("mail.smtp.password"));
        this.mailSender.setJavaMailProperties(properties);
    }

    @Test
    public void sendMail () throws MessagingException {
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
        messageHelper.setFrom(FROM_ADDRESS);
        messageHelper.setTo(TO_ADDRESS);
        messageHelper.setSubject(SUBJECT);
        messageHelper.setText(CONTENT, false);
        this.mailSender.send(messageHelper.getMimeMessage());
    }
}