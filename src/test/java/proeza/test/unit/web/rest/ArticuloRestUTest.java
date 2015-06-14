package proeza.test.unit.web.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import proeza.test.unit.TestUtil;
import proeza.test.unit.web.WebMvcUnitTest;

import com.proeza.sgs.business.chart.MultiDataSetChartDefinition;
import com.proeza.sgs.business.service.IArticuloService;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ArticuloRestUTest extends WebMvcUnitTest {

    @Autowired
    private IArticuloService articuloService;

    @Override
    protected Object[] getMocks () {
        return new Object[] {this.articuloService};
    }

    @Test
    public void priceHistory () throws Exception {
        List<Double> data = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        MultiDataSetChartDefinition<String, Double> precioHistory = new MultiDataSetChartDefinition<>();
        precioHistory.setData(data);
        precioHistory.setLabels(labels);
        data.addAll(Arrays.asList(new Double[] {28D, 48D, 40D, 19D, 86D, 27D, 90D}));
        labels.addAll(Arrays.asList(new String[] {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio"}));
        when(this.articuloService.priceHistory("1")).thenReturn(precioHistory);
        this.mockMvc.perform(post("/rest/articulo/priceHistory/1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$.labels", hasSize(7)))
            .andExpect(jsonPath("$.data", hasSize(7)))
            .andExpect(jsonPath("$.data[0]", is(28D)));

        verify(this.articuloService, times(1)).priceHistory("1");
        verifyNoMoreInteractions(this.articuloService);
    }
}