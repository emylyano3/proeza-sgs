package proeza.test.integration.mail;

import java.util.Locale;

import javax.mail.MessagingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.proeza.security.entity.builder.UsuarioBuilder;
import com.proeza.sgs.system.mail.IMailManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MailServiceTestContext.class)
public class MailServiceTest {

	@Autowired
	private IMailManager	mailManager;

	@Test
	public void mailService () throws MessagingException {
		this.mailManager.sendRegisterEmail(
			new UsuarioBuilder().withId(1)
				.withAlias("emylyano3")
				.withNombre("Emiliano")
				.withApellido("Schiano")
				.withEmail("emylyano3@gmail.com")
				.build(),
			Locale.US
			);
	}
}