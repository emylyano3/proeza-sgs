package com.proeza.sgs.web.rest.chart;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proeza.core.util.date.DateUtil;
import com.proeza.sgs.business.chart.venta.UserSalesChartDefinition;
import com.proeza.sgs.business.service.IVentaChartService;

@RestController
@RequestMapping("rest/sales/stats")
public class VentaChartRestController {

    @Autowired
    private IVentaChartService ventaService;

    @RequestMapping(value = "userLastMonthsSales/{userAlias}/{months}", method = RequestMethod.POST)
    public UserSalesChartDefinition userLastMonthsSales (@PathVariable String userAlias, @PathVariable int months) {
        Date to = DateUtil.createNow();
        Date from = DateUtil.substractMonths(to, months);
        return this.ventaService.ventasMensuales(userAlias, from, to);
    }
}