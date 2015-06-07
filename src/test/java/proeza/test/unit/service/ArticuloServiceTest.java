package proeza.test.unit.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import proeza.test.unit.AbstractUnitTest;

import com.proeza.core.tracking.entity.Movimiento;
import com.proeza.core.tracking.entity.builder.MovimientoBuilder;
import com.proeza.sgs.business.chart.MultiDataSetChartDefinition;
import com.proeza.sgs.business.dao.IArticuloDao;
import com.proeza.sgs.business.entity.TipoEntidad;
import com.proeza.sgs.business.entity.TipoMovimiento;
import com.proeza.sgs.business.service.IArticuloService;
import com.proeza.sgs.business.service.impl.ArticuloService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.thymeleaf.util.DateUtils.*;

public class ArticuloServiceTest extends AbstractUnitTest {

    @Autowired
    private IArticuloDao      articuloDao;

    private IArticuloService  articuloService = new ArticuloService();

    private MovimientoBuilder movBuilder      = new MovimientoBuilder();

    @Before
    public void setup () {
        ReflectionTestUtils.setField(this.articuloService, "articuloDao", this.articuloDao);
    }

    @Test
    public void priceHistory () {
        List<Movimiento> movs = new ArrayList<Movimiento>();
        this.movBuilder.withId(1L);
        this.movBuilder.withIdEntidad(1L);
        this.movBuilder.withValorAnte("100");
        this.movBuilder.withValorPost("121.31");
        this.movBuilder.withFechaMovimiento(create(2015, 01, 15).getTime());
        this.movBuilder.withTipoEntidad(TipoEntidad.ARTICULO.getCodigo());
        this.movBuilder.withTipoMov(TipoMovimiento.MOD_PRECIO.getCodigo());
        movs.add(this.movBuilder.build());
        this.movBuilder.withId(2L);
        this.movBuilder.withValorAnte("121.31");
        this.movBuilder.withValorPost("131.10");
        this.movBuilder.withFechaMovimiento(create(2015, 02, 15).getTime());
        movs.add(this.movBuilder.build());
        this.movBuilder.withId(3L);
        this.movBuilder.withValorAnte("131.10");
        this.movBuilder.withValorPost("130");
        this.movBuilder.withFechaMovimiento(create(2015, 03, 15).getTime());
        movs.add(this.movBuilder.build());
        when(this.articuloDao.findMovimientosAsc("COD_1", TipoMovimiento.MOD_PRECIO.getCodigo())).thenReturn(movs);
        MultiDataSetChartDefinition<String, Double> result = this.articuloService.priceHistory("COD_1");
        assertNotNull("La salida NUNCA debe ser null", result);
        assertNotNull("El listado de precios no puede ser null", result.getData());
        assertEquals("El listado de precios debe tener 4 items", 4, result.getData().size());
        assertTrue(result.getData().get(0) == 100);
        assertTrue(result.getData().get(3) == 130);
    }

    @Override
    protected Object[] getMocks () {
        return new Object[] {this.articuloDao};
    }
}