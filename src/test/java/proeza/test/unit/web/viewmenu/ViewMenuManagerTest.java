package proeza.test.unit.web.viewmenu;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import proeza.test.unit.web.context.TestContext;

import com.proeza.sgs.config.dispatcher.WebMvcConfig;

/**
 * Arquitectura de testing unitario para la parte web de la aplicacion.<br/>
 * <a href=
 * "http://www.petrikainulainen.net/programming/spring-framework/unit-testing-of-spring-mvc-controllers-configuration/"
 * >Documentacion</a>
 *
 * @author c.eschia
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, WebMvcConfig.class})
@WebAppConfiguration
public abstract class ViewMenuManagerTest {
	protected MockMvc				mockMvc;

	@Autowired
	private WebApplicationContext	webApplicationContext;

	@Before
	public void setUp () {
		// We have to reset our mock between tests because the mock objects
		// are managed by the Spring container. If we would not reset them,
		// stubbing and verified behavior would "leak" from one test to another.
		Mockito.reset(getMocks());
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

	protected abstract Object[] getMocks ();
}