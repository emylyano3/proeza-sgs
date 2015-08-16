package proeza.test.integration.persistence;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import proeza.test.integration.IntegrationTest;

import com.proeza.sgs.business.dao.IVentaDao;
import com.proeza.sgs.business.entity.Venta;

public class VentaDalTest extends IntegrationTest {

    @Autowired
    private IVentaDao ventaDao;

    @Test
    public void findAll () {
        List<Venta> result = this.ventaDao.findAll();
        Assert.assertNotNull(result);
        Assert.assertFalse(result.isEmpty());
    }

    @Test
    public void chart_WORST_SELLERS () {
    }
}