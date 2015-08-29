package proeza.test.integration.mail;

import java.util.Locale;

import javax.mail.MessagingException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import proeza.test.integration.IntegrationTest;

import com.proeza.security.entity.builder.UsuarioBuilder;
import com.proeza.sgs.system.mail.IMailManager;

public class MailServiceTest extends IntegrationTest {

    @Autowired
    private IMailManager mailManager;

    @Test
    public void mailService () throws MessagingException {
        this.mailManager.sendRegisterEmail(
            new UsuarioBuilder().withId(1L)
                .withAlias("emylyano3")
                .withNombre("Emiliano")
                .withApellido("Schiano")
                .withEmail("emylyano3@gmail.com")
                .build(),
            Locale.US
            );
    }
}