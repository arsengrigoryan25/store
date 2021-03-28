package com.store.agdemo.model;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;


public class ProductModel {
    public Long id;
    @NotBlank
    public String title;
    @DecimalMin("0.001")
    public BigDecimal price;
    @NotBlank
    public String description;
    public Long categoryId;

}
