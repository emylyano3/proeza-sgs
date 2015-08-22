package proeza.test.integration.persistence;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import proeza.test.integration.IntegrationTest;

import com.proeza.sgs.business.dao.IArticuloDao;
import com.proeza.sgs.business.entity.Articulo;

public class ArticuloDalTest extends IntegrationTest {

    @Autowired
    private IArticuloDao articleDao;

    @Test
    public void findAll () {
        List<Articulo> result = this.articleDao.findAll();
        Assert.assertNotNull(result);
        Assert.assertFalse(result.isEmpty());
    }
}