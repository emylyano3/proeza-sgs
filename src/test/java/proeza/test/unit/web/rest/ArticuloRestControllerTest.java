package proeza.test.unit.web.rest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import proeza.test.unit.TestUtil;
import proeza.test.unit.web.WebMvcUnitTest;

import com.proeza.sgs.business.dto.service.PrecioDTO;
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
		List<PrecioDTO> data = new ArrayList<PrecioDTO>();
		data.add(new PrecioDTO(12.3, getDate(1, 1, 2015)));
		data.add(new PrecioDTO(13.0, getDate(2, 2, 2015)));
		data.add(new PrecioDTO(13.2, getDate(6, 3, 2015)));
		data.add(new PrecioDTO(14.0, getDate(2, 4, 2015)));
		data.add(new PrecioDTO(14.0, getDate(4, 5, 2015)));
		data.add(new PrecioDTO(13.9, getDate(9, 6, 2015)));
		when(this.articuloService.priceHistory("1")).thenReturn(data);
		this.mockMvc.perform(post("/rest/articulo/priceHistory/1"))
		    .andExpect(status().isOk())
		    .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
		    .andExpect(jsonPath("$", hasSize(6)))
		    .andExpect(jsonPath("$[0].precio", is(12.3)))
		    .andExpect(jsonPath("$[1].precio", is(13.0)))
		    .andExpect(jsonPath("$[2].precio", is(13.2)))
		    .andExpect(jsonPath("$[3].fecha", is("2/4/2015")))
		    .andExpect(jsonPath("$[4].fecha", is("4/5/2015")))
		    .andExpect(jsonPath("$[5].fecha", is("9/6/2015")));

		verify(this.articuloService, times(1)).priceHistory("1");
		verifyNoMoreInteractions(this.articuloService);
	}

	private String getDate (int day, int month, int year) {
		// Calendar c = Calendar.getInstance();
		// c.set(Calendar.DATE, day);
		// c.set(Calendar.MONTH, month);
		// c.set(Calendar.YEAR, year);
		return day + "/" + month + "/" + year;
	}
}