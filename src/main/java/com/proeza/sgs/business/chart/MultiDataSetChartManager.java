package com.proeza.sgs.business.chart;

import java.util.List;

public abstract class MultiDataSetChartManager<S> {

    public MultiDataSetChartDefinition getChartDefinition (List<S> source) {
        init(source);
        List<?> data = buildData();
        List<String> labels = buildLabels();
        return buildDefinition(labels, data);
    }

    protected abstract void init (List<S> source);

    protected abstract List<String> buildLabels ();

    protected abstract List<?> buildData ();

    protected abstract MultiDataSetChartDefinition buildDefinition (List<String> labels, List<?> data);
}