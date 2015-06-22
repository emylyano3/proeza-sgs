package com.proeza.sgs.business.chart.articulo;

import com.proeza.sgs.business.chart.SingleDataSetChartDefinition;

public class BestSellersChartDefinition extends SingleDataSetChartDefinition {
    private static final long serialVersionUID = 1L;

    public BestSellersChartDefinition (String label, Integer value, String color, String highlight) {
        super(label, value, color, highlight);
    }
}