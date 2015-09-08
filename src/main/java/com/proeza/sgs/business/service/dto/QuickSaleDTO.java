package com.proeza.sgs.business.service.dto;

public class QuickSaleDTO {

    private String productCode;
    private String saleType;
    private String user;

    public String getProductCode () {
        return this.productCode;
    }

    public void setProductCode (String productCode) {
        this.productCode = productCode;
    }

    public String getSaleType () {
        return this.saleType;
    }

    public void setSaleType (String type) {
        this.saleType = type;
    }

    public String getUser () {
        return this.user;
    }

    public void setUser (String user) {
        this.user = user;
    }

    @Override
    public String toString () {
        return "QuickSaleDTO [productCode=" + this.productCode + ", type=" + this.saleType + "]";
    }
}