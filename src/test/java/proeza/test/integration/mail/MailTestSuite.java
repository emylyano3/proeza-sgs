package proeza.test.integration.mail;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    MailSenderTest.class,
    MailServiceTest.class
})
public class MailTestSuite {
}