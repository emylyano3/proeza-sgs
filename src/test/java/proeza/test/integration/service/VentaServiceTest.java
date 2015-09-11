package proeza.test.integration.service;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import proeza.test.integration.IntegrationTest;

import com.proeza.core.util.date.DateUtil;
import com.proeza.sgs.business.dao.IMedioPagoDao;
import com.proeza.sgs.business.entity.MedioPago;
import com.proeza.sgs.business.entity.Venta;
import com.proeza.sgs.business.entity.builder.VentaBuilder;
import com.proeza.sgs.business.service.IVentaChartService;
import com.proeza.sgs.business.service.IVentaService;

import static org.junit.Assert.*;

public class VentaServiceTest extends IntegrationTest {

    @Autowired
    private IVentaService      ventaService;
    @Autowired
    private IVentaChartService ventaChartService;

    @Autowired
    private IMedioPagoDao      medioPagoDao;

    @Test
    public void createSaleCode () {
        MedioPago pago = this.medioPagoDao.findByCode(IVentaService.PAGO_CREDITO);
        VentaBuilder builder = new VentaBuilder();
        builder.withMedioPago(pago);
        builder.withFecha(DateUtil.create(2015, 9, 5));
        builder.withImporte(BigDecimal.valueOf(1000));
        Venta venta = builder.build();
        String code = this.ventaService.createSaleCode(venta);
        assertNotNull(code);
        assertEquals("N15TCR0011", code);
    }

    @Test
    public void ventasMensuales () {
        this.ventaChartService.ventasMensuales(
            "admin",
            DateUtil.create(2015, 2, 1),
            DateUtil.create(2015, 8, 1)
            );
    }
}