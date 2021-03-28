package com.store.agdemo.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

public class RateModel implements Serializable {
    public Long productId;
    public Long userId;
    public String comment;
    @Min(value= 1)
    @Max(value= 5)
    public Integer rate;

    @Override
    public String toString() {
        return "RateModel{" +
                "productId=" + productId +
                ", userId=" + userId +
                ", comment='" + comment + '\'' +
                ", rate=" + rate +
                '}';
    }
}
