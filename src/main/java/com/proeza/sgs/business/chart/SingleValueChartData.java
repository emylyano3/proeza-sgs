package com.proeza.sgs.business.chart;

import java.io.Serializable;

public class SingleValueChartData implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer           value;
    private String            color;
    private String            highlight;
    private String            label;

    public SingleValueChartData () {
    }

    public SingleValueChartData (String label, Integer value, String color, String highlight) {
        this.label = label;
        this.value = value;
        this.color = color;
        this.highlight = highlight;
    }

    public Integer getValue () {
        return this.value;
    }

    public void setValue (Integer value) {
        this.value = value;
    }

    public String getColor () {
        return this.color;
    }

    public void setColor (String color) {
        this.color = color;
    }

    public String getHighlight () {
        return this.highlight;
    }

    public void setHighlight (String highlight) {
        this.highlight = highlight;
    }

    public String getLabel () {
        return this.label;
    }

    public void setLabel (String label) {
        this.label = label;
    }

    @Override
    public String toString () {
        return "SingleDataSetChartDefinition [value=" + this.value + ", label=" + this.label + "]";
    }
}