package proeza.test.integration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import com.proeza.sgs.config.env.Environments;
import com.proeza.sgs.config.root.ContextConfig;

@Configuration
@ActiveProfiles(profiles = "test")
@Import({Environments.class, ContextConfig.class})
public class IntegrationTestContext {

}