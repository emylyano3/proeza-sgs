package proeza.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import proeza.test.integration.IntegrationTestSuite;
import proeza.test.unit.UnitTestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    UnitTestSuite.class,
    IntegrationTestSuite.class
})
public class ApplicationTestSuite {
}