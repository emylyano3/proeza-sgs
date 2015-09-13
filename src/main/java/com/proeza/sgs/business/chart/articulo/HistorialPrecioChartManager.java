package com.proeza.sgs.business.chart.articulo;

import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.proeza.core.util.date.DateUtil;
import com.proeza.sgs.business.chart.MovimientoChartManager;
import com.proeza.sgs.business.chart.MultiValueChartData;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class HistorialPrecioChartManager extends MovimientoChartManager {

    @Override
    protected MultiValueChartData buildDefinition (List<String> labels, List<?> data) {
        HistorialPrecioChartDefinition definition = new HistorialPrecioChartDefinition(labels, data);
        definition.setUpdates(12);
        definition.setStock(10);
        definition.setLastPriceUpdate(DateUtil.createNow());
        definition.setLastStockUpdate(DateUtil.createNow());
        return definition;
    }
}