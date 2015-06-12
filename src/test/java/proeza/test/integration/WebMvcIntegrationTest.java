package proeza.test.integration;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.proeza.sgs.config.dispatcher.WebMvcConfig;
import com.proeza.sgs.config.env.Environments;
import com.proeza.sgs.config.root.ContextConfig;

@WebAppConfiguration
@ContextConfiguration(classes = {Environments.class, WebMvcConfig.class, ContextConfig.class})
public abstract class WebMvcIntegrationTest extends IntegrationTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    protected MockMvc             mockMvc;

    @Before
    public void setUp () {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }
}