package proeza.test.integration;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.proeza.sgs.config.env.Environments;
import com.proeza.sgs.config.init.MvcInitializer;
import com.proeza.sgs.config.root.ContextConfig;

@Transactional
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Environments.class, ContextConfig.class})
public abstract class IntegrationTest {
    @SuppressWarnings("unused")
    private MvcInitializer initializer = new MvcInitializer();
}