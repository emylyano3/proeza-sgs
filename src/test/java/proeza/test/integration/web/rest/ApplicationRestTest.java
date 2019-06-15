package proeza.test.integration.web.rest;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import proeza.test.integration.WebMvcIntegrationTest;

public class ApplicationRestTest extends WebMvcIntegrationTest {

	@Test
	public void priceHistory() throws Exception {
		this.mockMvc
		.perform(get("/api/menu/MAIN/admin"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.items", hasSize(3)))
		.andExpect(jsonPath("$.items[1].subitems", hasSize(1)))
		.andExpect(jsonPath("$.code", is("MAIN")));
	}
}