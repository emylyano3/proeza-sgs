package com.proeza.sgs.business.chart;

import java.io.Serializable;
import java.util.List;

public class MultiValueChartData implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<String>      labels;
    private List<?>           data;

    public MultiValueChartData (List<String> labels, List<?> data) {
        this.data = data;
        this.labels = labels;
    }

    public List<String> getLabels () {
        return this.labels;
    }

    public void setLabels (List<String> labels) {
        this.labels = labels;
    }

    public List<?> getData () {
        return this.data;
    }

    public void setData (List<?> data) {
        this.data = data;
    }
}