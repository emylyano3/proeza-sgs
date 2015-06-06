package proeza.test.unit.web.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import proeza.test.unit.TestUtil;
import proeza.test.unit.web.WebMvcUnitTest;

import com.proeza.sgs.business.dto.service.PrecioHistoryDTO;
import com.proeza.sgs.business.service.IArticuloService;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ArticuloRestControllerTest extends WebMvcUnitTest {

	@Autowired
	private IArticuloService	articuloService;

	@Override
	protected Object[] getMocks () {
		return new Object[] {this.articuloService};
	}

	@Test
	public void priceHistory () throws Exception {
		List<Double> data = new ArrayList<>();
		PrecioHistoryDTO precioHistory = new PrecioHistoryDTO(data, null);
		data.addAll(Arrays.asList(new Double[] {28D, 48D, 40D, 19D, 86D, 27D, 90D}));
		when(this.articuloService.priceHistory("1")).thenReturn(precioHistory);
		this.mockMvc.perform(post("/rest/articulo/priceHistory/1"))
		    .andExpect(status().isOk())
		    .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
		    .andExpect(jsonPath("$", hasSize(1)))
		    .andExpect(jsonPath("$[0].prices", hasSize(7)))
		    .andExpect(jsonPath("$[0].prices[0]", is(28D)));

		verify(this.articuloService, times(1)).priceHistory("1");
		verifyNoMoreInteractions(this.articuloService);
	}
}