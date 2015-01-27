package proeza.test.unit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import proeza.test.unit.settings.SettingsTest;
import proeza.test.unit.web.controller.HomeControllerTest;
import proeza.test.unit.web.controller.UsuarioControllerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	HomeControllerTest.class,
	UsuarioControllerTest.class,
	SettingsTest.class
})
public class UnitTestSuite {}