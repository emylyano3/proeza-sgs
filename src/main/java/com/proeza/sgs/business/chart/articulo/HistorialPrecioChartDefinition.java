package com.proeza.sgs.business.chart.articulo;

import java.util.Date;
import java.util.List;

import com.proeza.sgs.business.chart.MultiValueChartData;

public class HistorialPrecioChartDefinition extends MultiValueChartData {
    private static final long serialVersionUID = 1L;

    private Date              lastStockUpdate;
    private Date              lastPriceUpdate;
    private Integer           updates;
    private Integer           stock;

    public HistorialPrecioChartDefinition (List<String> labels, List<?> data) {
        super(labels, data);
    }

    public Integer getUpdates () {
        return this.updates;
    }

    public void setUpdates (Integer updates) {
        this.updates = updates;
    }

    public Integer getStock () {
        return this.stock;
    }

    public void setStock (Integer stock) {
        this.stock = stock;
    }

    public Date getLastStockUpdate () {
        return this.lastStockUpdate;
    }

    public void setLastStockUpdate (Date lastStockUpdate) {
        this.lastStockUpdate = lastStockUpdate;
    }

    public Date getLastPriceUpdate () {
        return this.lastPriceUpdate;
    }

    public void setLastPriceUpdate (Date lastPriceUpdate) {
        this.lastPriceUpdate = lastPriceUpdate;
    }
}