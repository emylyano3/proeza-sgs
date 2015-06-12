package proeza.test.integration.web.rest;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import proeza.test.integration.WebMvcIntegrationTest;
import proeza.test.unit.TestUtil;

import com.proeza.sgs.business.dao.IArticuloDao;
import com.proeza.sgs.business.entity.Articulo;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ArticuloRestITest extends WebMvcIntegrationTest {

    @Autowired
    private IArticuloDao articuloDao;

    @Test
    public void priceHistory () throws Exception {
        this.mockMvc.perform(post("/rest/articulo/priceHistory/PRRS000001"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$.labels", hasSize(5)))
            .andExpect(jsonPath("$.data", hasSize(5)))
            .andExpect(jsonPath("$.data[0]", is(270D)));
    }

    @Test
    public void priceHistory_ACTUALIZANDO_ARTICULO () throws Exception {
        Articulo articulo = this.articuloDao.findByCode("PRRS000001");
        assertNotNull(articulo);
        this.articuloDao.persist(articulo);
        articulo.setPrecio(BigDecimal.valueOf(275.12));
        this.mockMvc.perform(post("/rest/articulo/priceHistory/PRRS000001"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$.labels", hasSize(6)))
            .andExpect(jsonPath("$.data", hasSize(6)))
            .andExpect(jsonPath("$.data[0]", is(270D)));
    }
}