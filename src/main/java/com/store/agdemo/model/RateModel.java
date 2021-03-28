package com.store.agdemo.model;

import javax.validation.constraints.Size;

public class RateModel {
    public Long productId;
    public Long userId;
    public String comment;
//    @Size(min = 1, max = 5)
    public Integer rate;

}
