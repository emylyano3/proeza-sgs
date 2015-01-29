package proeza.test.integration;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.proeza.sgs.config.init.MvcInitializer;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
@ContextConfiguration(classes = {IntegrationTestContext.class})
public abstract class IntegrationTest {
	@SuppressWarnings("unused")
	private MvcInitializer initializer = new MvcInitializer();
}