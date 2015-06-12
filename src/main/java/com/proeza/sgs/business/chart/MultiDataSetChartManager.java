package com.proeza.sgs.business.chart;

import java.util.List;

public abstract class MultiDataSetChartManager<S, L, D> {

    @SuppressWarnings({"unchecked", "rawtypes"})
    public MultiDataSetChartDefinition<L, D> getChartDefinition (List<S> source) {
        init(source);
        return new MultiDataSetChartDefinition(buildLabels(), buildData());
    }

    protected abstract void init (List<S> source);

    protected abstract List<L> buildLabels ();

    protected abstract List<D> buildData ();
}