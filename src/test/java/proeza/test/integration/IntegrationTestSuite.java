package proeza.test.integration;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import proeza.test.integration.form.FormValidationTest;
import proeza.test.integration.mail.MailTestSuite;
import proeza.test.integration.persistence.DalTestSuite;
import proeza.test.integration.tracking.TrackingTest;
import proeza.test.integration.web.rest.ArticuloRestTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    DalTestSuite.class,
    MailTestSuite.class,
    FormValidationTest.class,
    TrackingTest.class,
    ArticuloRestTest.class
})
public class IntegrationTestSuite {
}