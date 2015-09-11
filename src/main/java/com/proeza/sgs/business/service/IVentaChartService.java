package com.proeza.sgs.business.service;

import java.util.Date;

import com.proeza.sgs.business.chart.venta.UserSalesChartDefinition;

public interface IVentaChartService {

    UserSalesChartDefinition ventasMensuales (String aliasUsuario, Date desde, Date hasta);

}