package com.proeza.sgs.business.chart;

public class ChartColor {

    private String color;
    private String hlColor;

    public ChartColor (String color, String highlightColor) {
        this.color = color;
        this.hlColor = highlightColor;
    }

    public String getColor () {
        return this.color;
    }

    public String getHighlightColor () {
        return this.hlColor;
    }

    @Override
    public String toString () {
        return "ChartColor [color=" + this.color + ", hlColor=" + this.hlColor + "]";
    }
}