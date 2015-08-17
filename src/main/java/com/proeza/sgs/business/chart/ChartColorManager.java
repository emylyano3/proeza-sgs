package com.proeza.sgs.business.chart;

import java.util.ArrayList;
import java.util.List;

public class ChartColorManager {

    private List<ChartColor> colors = new ArrayList<ChartColor>(0);

    public void setColors (List<ChartColor> colors) {
        this.colors = colors;
    }

    public void addColor (ChartColor color) {
        this.colors.add(color);
    }

    private int currentColor;

    public ChartColor nextChartColor () {
        if (this.currentColor >= this.colors.size()) {
            this.currentColor = 0;
        }
        return this.colors.get(this.currentColor++);
    }
}