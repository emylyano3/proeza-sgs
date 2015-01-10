package proeza.test.integration.mail;

import java.util.Locale;

import javax.mail.MessagingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.proeza.core.service.MailService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MailServiceTestContext.class)
public class MailServiceTest {

	@Autowired
	private MailService		mailService;

	@Test
	public void mailService () throws MessagingException {
		this.mailService.sendContactEmail(
			"Emiliano",
			"emylyano3@gmail.com",
			Locale.US
			);
	}
}