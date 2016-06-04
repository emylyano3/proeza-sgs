package proeza.test.integration.crones;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.proeza.sgs.business.scheduling.JobRelevamiento;
import com.proeza.sgs.config.env.Environments;
import com.proeza.sgs.config.root.ContextConfig;

@Transactional
@ActiveProfiles("dev")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Environments.class, ContextConfig.class})
public class StockCronesQueriesTest // extends IntegrationTest
{

    @Autowired
    private JobRelevamiento job;

    @Test
    public void countStock_POR_MARCA () {
        this.job.countStockByBrand();
    }

    @Test
    public void countStock_POR_RUBRO () {
        this.job.countStockByBrand();
    }
}