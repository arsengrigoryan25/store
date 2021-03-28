package com.store.agdemo.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class RateModel {
    public Long productId;
    public Long userId;
    public String comment;
    @Min(value= 1, message = "Min value of rate is 1")
    @Max(value= 5, message = "Max value of rate is 5")
    public Integer rate;

}
