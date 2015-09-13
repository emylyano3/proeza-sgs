package proeza.test.unit.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;

import proeza.test.unit.AbstractUnitTest;

import com.proeza.core.tracking.entity.Movimiento;
import com.proeza.core.tracking.entity.builder.MovimientoBuilder;
import com.proeza.sgs.business.chart.articulo.HistorialPrecioChartDefinition;
import com.proeza.sgs.business.dao.IArticuloDao;
import com.proeza.sgs.business.entity.TipoEntidad;
import com.proeza.sgs.business.entity.TipoMovimiento;
import com.proeza.sgs.business.service.IArticuloChartService;
import com.proeza.sgs.business.service.impl.ArticuloChartService;

import static com.proeza.core.util.DataTypeConverter.*;
import static com.proeza.core.util.date.DateUtil.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ArticuloServiceTest extends AbstractUnitTest {

    @Autowired
    private IArticuloDao          articuloDao;

    @Autowired
    private ApplicationContext    context;

    private IArticuloChartService articuloService = new ArticuloChartService();

    private MovimientoBuilder     movBuilder      = new MovimientoBuilder();

    @Before
    public void setup () {
        ReflectionTestUtils.setField(this.articuloService, "articuloDao", this.articuloDao);
        ReflectionTestUtils.setField(this.articuloService, "context", this.context);
    }

    @Test
    public void priceHistory () {
        List<Movimiento> movs = new ArrayList<Movimiento>();
        this.movBuilder.withId(1L);
        this.movBuilder.withIdEntidad(1L);
        this.movBuilder.withValorAnte("100");
        this.movBuilder.withValorPost("121.31");
        this.movBuilder.withFecha(toTimestamp(create(2015, 01, 15)));
        this.movBuilder.withTipoEntidad(TipoEntidad.ARTICULO.codigo());
        this.movBuilder.withTipoMov(TipoMovimiento.MOD_PRECIO.codigo());
        movs.add(this.movBuilder.build());
        this.movBuilder.withId(2L);
        this.movBuilder.withValorAnte("121.31");
        this.movBuilder.withValorPost("131.10");
        this.movBuilder.withFecha(toTimestamp(create(2015, 02, 15)));
        movs.add(this.movBuilder.build());
        this.movBuilder.withId(3L);
        this.movBuilder.withValorAnte("131.10");
        this.movBuilder.withValorPost("130");
        this.movBuilder.withFecha(toTimestamp(create(2015, 03, 15)));
        movs.add(this.movBuilder.build());
        when(this.articuloDao.findMovimientosAscByDate("COD_1", TipoMovimiento.MOD_PRECIO.codigo())).thenReturn(movs);
        HistorialPrecioChartDefinition result = this.articuloService.priceHistory("COD_1");
        assertNotNull("La salida NUNCA debe ser null", result);
        assertNotNull("El listado de precios no puede ser null", result.getData());
        assertEquals("El listado de precios debe tener 3 items", 3, result.getData().size());
        assertTrue((Double) result.getData().get(0) == 121.31);
        assertTrue((Double) result.getData().get(2) == 130);
    }

    @Override
    protected Object[] getMocks () {
        return new Object[] {this.articuloDao};
    }
}