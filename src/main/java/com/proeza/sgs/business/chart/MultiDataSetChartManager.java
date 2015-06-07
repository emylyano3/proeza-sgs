package com.proeza.sgs.business.chart;

import java.util.List;

public abstract class MultiDataSetChartManager<S, L, D> {

    @SuppressWarnings({"unchecked", "rawtypes"})
    public MultiDataSetChartDefinition<L, D> getChartDefinition (List<S> source) {
        return new MultiDataSetChartDefinition(buildLabels(source), buildData(source));
    }

    protected abstract List<L> buildLabels (List<S> source);

    protected abstract List<D> buildData (List<S> source);
}