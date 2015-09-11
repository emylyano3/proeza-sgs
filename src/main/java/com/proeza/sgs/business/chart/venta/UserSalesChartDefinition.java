package com.proeza.sgs.business.chart.venta;

import java.util.List;

import com.proeza.sgs.business.chart.MultiDataSetChartDefinition;

public class UserSalesChartDefinition extends MultiDataSetChartDefinition {

    public UserSalesChartDefinition (List<String> labels, List<?> data) {
        super(labels, data);
    }

    private static final long serialVersionUID = 1L;

    private String            userAlias;

    public String getUserAlias () {
        return this.userAlias;
    }

    public void setUserAlias (String userAlias) {
        this.userAlias = userAlias;
    }
}