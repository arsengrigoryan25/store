package com.store.agdemo.model;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;

public class ProductModel implements Serializable {
    public Long id;
    @NotBlank
    public String title;
    @DecimalMin("0.001")
    public BigDecimal price;
    @NotBlank
    public String description;
    public Long categoryId;

    @Override
    public String toString() {
        return "ProductModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", categoryId=" + categoryId +
                '}';
    }
}
