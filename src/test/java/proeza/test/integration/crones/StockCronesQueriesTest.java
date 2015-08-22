package proeza.test.integration.crones;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import proeza.test.integration.IntegrationTest;

import com.proeza.sgs.business.dao.IArticuloDao;

public class StockCronesQueriesTest extends IntegrationTest {

    @Autowired
    private IArticuloDao articleDao;

    @Test
    public void countStock_POR_MARCA () {
        @SuppressWarnings("rawtypes")
        List result = this.articleDao.getEntityManager()
            .createNamedQuery("relevamientoStock.porMarca")
            .getResultList();
        System.out.println(result);
    }

    @Test
    public void countStock_POR_RUBRO () {
        @SuppressWarnings("rawtypes")
        List result = this.articleDao.getEntityManager()
            .createNamedQuery("relevamientoStock.porRubro")
            .getResultList();
        System.out.println(result);
    }
}