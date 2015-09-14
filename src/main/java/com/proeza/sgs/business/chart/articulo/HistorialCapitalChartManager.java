package com.proeza.sgs.business.chart.articulo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.proeza.sgs.business.chart.MovimientoChartManager;
import com.proeza.sgs.business.chart.StockChartData;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class HistorialCapitalChartManager extends MovimientoChartManager {

    @Override
    protected StockChartData buildDefinition (List<String> labels, List<?> data) {
        StockChartData definition = new StockChartData(labels, data);
        DateFormat pattern = new SimpleDateFormat("dd/MM/YYYY");
        definition.setLowestValue("$ " + getLowestValue());
        definition.setHighestValue("$ " + getHighestValue());
        definition.setLowestValueDate(pattern.format(getLowestValueDate()));
        definition.setHighestValueDate(pattern.format(getLowestValueDate()));
        return definition;
    }
}