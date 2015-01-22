package proeza.test.integration;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import proeza.test.integration.form.FormValidationTest;
import proeza.test.integration.mail.MailSenderTest;
import proeza.test.integration.mail.MailServiceTest;
import proeza.test.integration.persistence.NegocioDalTest;
import proeza.test.integration.persistence.SeguridadDalTest;
import proeza.test.integration.persistence.SistemaDalTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	NegocioDalTest.class,
	SistemaDalTest.class,
	SeguridadDalTest.class,
	MailSenderTest.class,
	MailServiceTest.class,
	FormValidationTest.class,
})
public class IntegrationTestSuite {
}