package proeza.test.integration.web.rest;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import proeza.test.integration.IntegrationTestUtil;
import proeza.test.integration.WebMvcIntegrationTest;
import proeza.test.unit.TestUtil;

import com.proeza.sgs.business.dao.IArticuloDao;
import com.proeza.sgs.business.entity.Articulo;
import com.proeza.sgs.business.entity.dto.ArticuloDTO;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ArticuloRestTest extends WebMvcIntegrationTest {

    @Autowired
    private IArticuloDao articuloDao;

    @Test
    public void priceHistory () throws Exception {
        this.mockMvc
        .perform(post("/rest/product/stats/priceHistory/PRRS000001"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.labels", hasSize(5)))
        .andExpect(jsonPath("$.data", hasSize(5)))
        .andExpect(jsonPath("$.data[0]", is(270D)));
    }

    @Test
    public void priceHistory_ACTUALIZANDO_ARTICULO_UNA_VEZ () throws Exception {
        Articulo articulo = this.articuloDao.findByCode("PRRS000001");
        assertNotNull(articulo);
        articulo.setPrecio(BigDecimal.valueOf(275.12));
        this.articuloDao.persist(articulo);
        this.mockMvc.perform(post("/rest/product/stats/priceHistory/PRRS000001"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.labels", hasSize(10)))
        .andExpect(jsonPath("$.data", hasSize(10)))
        .andExpect(jsonPath("$.data[0]", is(325D)));
    }

    @Test
    public void priceHistory_ACTUALIZANDO_ARTICULO_N_VECES () throws Exception {
        Articulo articulo = this.articuloDao.findByCode("PRRS000001");
        assertNotNull(articulo);
        articulo.setPrecio(BigDecimal.valueOf(330.12));
        this.articuloDao.persist(articulo);
        Thread.sleep(100);
        articulo.setPrecio(BigDecimal.valueOf(350.0));
        this.articuloDao.persist(articulo);
        Thread.sleep(100);
        articulo.setPrecio(BigDecimal.valueOf(900));
        this.articuloDao.persist(articulo);
        this.mockMvc.perform(post("/rest/product/stats/priceHistory/PRRS000001"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.labels", hasSize(10)))
        .andExpect(jsonPath("$.data", hasSize(10)))
        .andExpect(jsonPath("$.data[0]", is(325D)))
        .andExpect(jsonPath("$.data[8]", is(325D)));
    }

    @Test
    public void update () throws Exception {
        Articulo articulo = this.articuloDao.findByCode("PRRS000001");
        assertNotNull(articulo);
        ArticuloDTO articuloDTO = new ArticuloDTO(articulo);
        articuloDTO.setPrecio(350D);
        this.mockMvc.perform(
                post("/rest/product/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(IntegrationTestUtil.objectToJsonBytes(articuloDTO))
                );
        articulo = this.articuloDao.findByCode("PRRS000001");
        assertNotNull(articulo);
        assertNotNull(articulo.getMovimientos());
        assertFalse(articulo.getMovimientos().isEmpty());
        assertEquals(articulo.getPrecio(), BigDecimal.valueOf(350.0));
    }
}