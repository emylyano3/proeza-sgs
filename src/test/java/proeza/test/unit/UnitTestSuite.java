package proeza.test.unit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import proeza.test.unit.settings.SettingsTest;
import proeza.test.unit.viewmenu.ViewMenuManagerTest;
import proeza.test.unit.web.controller.ControllerTestSuite;
import proeza.test.unit.web.rest.RestControllerTestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    ControllerTestSuite.class,
    RestControllerTestSuite.class,
    ViewMenuManagerTest.class,
    SettingsTest.class
})
public class UnitTestSuite {
}