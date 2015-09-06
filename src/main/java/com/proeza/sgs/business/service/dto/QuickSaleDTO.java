package com.proeza.sgs.business.service.dto;

public class QuickSaleDTO {

    private String productCode;
    private String type;

    public String getProductCode () {
        return this.productCode;
    }

    public void setProductCode (String productCode) {
        this.productCode = productCode;
    }

    public String getSaleType () {
        return this.type;
    }

    public void setType (String type) {
        this.type = type;
    }

    @Override
    public String toString () {
        return "QuickSaleDTO [productCode=" + this.productCode + ", type=" + this.type + "]";
    }
}