package com.proeza.sgs.business.chart;

import java.io.Serializable;
import java.util.List;

public class MultiDataSetChartDefinition<L, D> implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<L>           labels;
    private List<D>           data;

    public MultiDataSetChartDefinition () {
    }

    public MultiDataSetChartDefinition (List<L> labels, List<D> data) {
        this.data = data;
        this.labels = labels;
    }

    public List<L> getLabels () {
        return this.labels;
    }

    public void setLabels (List<L> labels) {
        this.labels = labels;
    }

    public List<D> getData () {
        return this.data;
    }

    public void setData (List<D> data) {
        this.data = data;
    }
}