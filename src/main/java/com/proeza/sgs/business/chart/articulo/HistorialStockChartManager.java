package com.proeza.sgs.business.chart.articulo;

import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.proeza.sgs.business.chart.MovimientoChartManager;
import com.proeza.sgs.business.chart.StockChartData;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class HistorialStockChartManager extends MovimientoChartManager {

    @Override
    protected StockChartData buildDefinition(List<String> labels, List<?> data) {
        StockChartData definition = new StockChartData(labels, data);
        definition.setLowestValue("" + getLowestValue().intValue());
        definition.setHighestValue("" + getHighestValue().intValue());
        definition.setLowestValueDate(formatDate(getLowestValueDate()));
        definition.setHighestValueDate(formatDate(getHighestValueDate()));
        return definition;
    }
}