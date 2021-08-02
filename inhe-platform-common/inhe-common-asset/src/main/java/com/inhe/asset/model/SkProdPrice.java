package com.inhe.asset.model;

import java.math.BigDecimal;

public class SkProdPrice {
    private String id;

    private BigDecimal buyCost;

    private BigDecimal cost1;

    private BigDecimal cost2;

    private BigDecimal cost3;

    private BigDecimal cost4;

    private BigDecimal costHigh;

    private BigDecimal costLow;

    private BigDecimal costLastest;

    private BigDecimal price1;

    private BigDecimal price2;

    private BigDecimal price3;

    private BigDecimal price4;

    private BigDecimal priceHigh;

    private BigDecimal priceLow;

    private BigDecimal priceLastest;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public BigDecimal getBuyCost() {
        return buyCost;
    }

    public void setBuyCost(BigDecimal buyCost) {
        this.buyCost = buyCost;
    }

    public BigDecimal getCost1() {
        return cost1;
    }

    public void setCost1(BigDecimal cost1) {
        this.cost1 = cost1;
    }

    public BigDecimal getCost2() {
        return cost2;
    }

    public void setCost2(BigDecimal cost2) {
        this.cost2 = cost2;
    }

    public BigDecimal getCost3() {
        return cost3;
    }

    public void setCost3(BigDecimal cost3) {
        this.cost3 = cost3;
    }

    public BigDecimal getCost4() {
        return cost4;
    }

    public void setCost4(BigDecimal cost4) {
        this.cost4 = cost4;
    }

    public BigDecimal getCostHigh() {
        return costHigh;
    }

    public void setCostHigh(BigDecimal costHigh) {
        this.costHigh = costHigh;
    }

    public BigDecimal getCostLow() {
        return costLow;
    }

    public void setCostLow(BigDecimal costLow) {
        this.costLow = costLow;
    }

    public BigDecimal getCostLastest() {
        return costLastest;
    }

    public void setCostLastest(BigDecimal costLastest) {
        this.costLastest = costLastest;
    }

    public BigDecimal getPrice1() {
        return price1;
    }

    public void setPrice1(BigDecimal price1) {
        this.price1 = price1;
    }

    public BigDecimal getPrice2() {
        return price2;
    }

    public void setPrice2(BigDecimal price2) {
        this.price2 = price2;
    }

    public BigDecimal getPrice3() {
        return price3;
    }

    public void setPrice3(BigDecimal price3) {
        this.price3 = price3;
    }

    public BigDecimal getPrice4() {
        return price4;
    }

    public void setPrice4(BigDecimal price4) {
        this.price4 = price4;
    }

    public BigDecimal getPriceHigh() {
        return priceHigh;
    }

    public void setPriceHigh(BigDecimal priceHigh) {
        this.priceHigh = priceHigh;
    }

    public BigDecimal getPriceLow() {
        return priceLow;
    }

    public void setPriceLow(BigDecimal priceLow) {
        this.priceLow = priceLow;
    }

    public BigDecimal getPriceLastest() {
        return priceLastest;
    }

    public void setPriceLastest(BigDecimal priceLastest) {
        this.priceLastest = priceLastest;
    }
}