package com.proeza.sgs.business.chart;

import java.util.List;

public class StockChartData extends MultiValueChartData {

    public StockChartData (List<String> labels, List<?> data) {
        super(labels, data);
    }

    private static final long serialVersionUID = 1L;

    private String            currentValue;
    private String            lowestValue;
    private String            lowestValueDate;
    private String            highestValue;
    private String            highestValueDate;

    public String getCurrentValue () {
        return this.currentValue;
    }

    public void setCurrentValue (String currentValue) {
        this.currentValue = currentValue;
    }

    public String getLowestValue () {
        return this.lowestValue;
    }

    public void setLowestValue (String lowestValue) {
        this.lowestValue = lowestValue;
    }

    public String getLowestValueDate () {
        return this.lowestValueDate;
    }

    public void setLowestValueDate (String lowestValueDate) {
        this.lowestValueDate = lowestValueDate;
    }

    public String getHighestValue () {
        return this.highestValue;
    }

    public void setHighestValue (String highestValue) {
        this.highestValue = highestValue;
    }

    public String getHighestValueDate () {
        return this.highestValueDate;
    }

    public void setHighestValueDate (String highestValueDate) {
        this.highestValueDate = highestValueDate;
    }
}