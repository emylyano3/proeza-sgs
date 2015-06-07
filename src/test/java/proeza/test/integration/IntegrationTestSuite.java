package proeza.test.integration;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import proeza.test.integration.form.FormValidationTest;
import proeza.test.integration.mail.MailTestSuite;
import proeza.test.integration.persistence.DalTestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    DalTestSuite.class,
    MailTestSuite.class,
    FormValidationTest.class,
})
public class IntegrationTestSuite {
}