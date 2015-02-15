package proeza.test.integration;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.proeza.sgs.config.init.MvcInitializer;
import com.proeza.sgs.config.root.ContextConfig;

@ActiveProfiles(profiles = "test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.proeza.sgs.config.env.Test.class, ContextConfig.class}, loader = AnnotationConfigContextLoader.class)
public abstract class IntegrationTest {
	@SuppressWarnings("unused")
	private MvcInitializer initializer = new MvcInitializer();
}