package com.store.agdemo.filter;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class ProductFilter implements Serializable {

    private String name;
    private Integer category;
    @Positive
    private Integer fromPrice;
    @Positive
    private Integer toPrice;
//    @Size(min = 1, max = 5)
    private Integer fromRate;
//    @Size(min = 1, max = 5)
    private Integer toRate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getFromPrice() {
        return fromPrice;
    }

    public void setFromPrice(Integer fromPrice) {
        this.fromPrice = fromPrice;
    }

    public Integer getToPrice() {
        return toPrice;
    }

    public void setToPrice(Integer toPrice) {
        this.toPrice = toPrice;
    }

    public Integer getFromRate() {
        return fromRate;
    }

    public void setFromRate(Integer fromRate) {
        this.fromRate = fromRate;
    }

    public Integer getToRate() {
        return toRate;
    }

    public void setToRate(Integer toRate) {
        this.toRate = toRate;
    }
}