package com.proeza.sgs.business.service.dto;

import java.io.Serializable;
import java.util.List;

public class PrecioHistoryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<String>      labels;
    private List<Double>      prices;

    public PrecioHistoryDTO () {
    }

    public PrecioHistoryDTO (List<Double> prices, List<String> labels) {
        this.prices = prices;
        this.labels = labels;
    }

    public List<Double> getPrices () {
        return this.prices;
    }

    public void setPrices (List<Double> prices) {
        this.prices = prices;
    }

    public List<String> getLabels () {
        return this.labels;
    }

    public void setLabels (List<String> labels) {
        this.labels = labels;
    }
}