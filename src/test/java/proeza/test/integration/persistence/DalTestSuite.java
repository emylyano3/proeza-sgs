package proeza.test.integration.persistence;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    NegocioDalTest.class,
    SistemaDalTest.class,
    SeguridadDalTest.class
})
public class DalTestSuite {
}