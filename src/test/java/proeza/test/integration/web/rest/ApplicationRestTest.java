package proeza.test.integration.web.rest;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;

import proeza.test.integration.WebMvcIntegrationTest;

public class ApplicationRestTest extends WebMvcIntegrationTest {

    @Test
    public void priceHistory() throws Exception {
        this.mockMvc
        .perform(post("/rest/application/getMenu/MAIN"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.items", hasSize(3)))
        .andExpect(jsonPath("$.items[1].subitems", hasSize(4)))
        .andExpect(jsonPath("$.name", is("MAIN")));
    }
}