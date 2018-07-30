package com.csii.integralmall.bean;

public class WonderfulActivityInfo {
    private boolean isHot = false;
    private String productName;
    private String productDes;
    private int integralPrice;
    private int surplusNum;
    private int productImage;


    public WonderfulActivityInfo(boolean isHot, String productName, String productDes, int integralPrice, int surplusNum, int productImage) {
        this.isHot = isHot;
        this.productName = productName;
        this.productDes = productDes;
        this.integralPrice = integralPrice;
        this.surplusNum = surplusNum;
        this.productImage = productImage;
    }

    public boolean isHot() {
        return isHot;
    }

    public void setHot(boolean hot) {
        isHot = hot;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDes() {
        return productDes;
    }

    public void setProductDes(String productDes) {
        this.productDes = productDes;
    }

    public int getIntegralPrice() {
        return integralPrice;
    }

    public void setIntegralPrice(int integralPrice) {
        this.integralPrice = integralPrice;
    }

    public int getSurplusNum() {
        return surplusNum;
    }

    public void setSurplusNum(int surplusNum) {
        this.surplusNum = surplusNum;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    @Override
    public String toString() {
        return "WonderfulActivityInfo{" +
                "isHot=" + isHot +
                ", productName='" + productName + '\'' +
                ", productDes='" + productDes + '\'' +
                ", integralPrice=" + integralPrice +
                ", surplusNum=" + surplusNum +
                ", productImage=" + productImage +
                '}';
    }
}
