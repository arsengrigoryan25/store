package com.store.agdemo.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
public class Product extends IdentifiedEntity<Long> {
    @Column
    private String title;
    @Column
    private BigDecimal price;
    @Column
    private String description;
    @Column
    private BigDecimal rateAvg;

    @OneToMany(mappedBy = "product")
    private Set<Rate> rates;

    @ManyToOne
    @JoinColumn(name = "category_Id", nullable = false)
    private Category category;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getRateAvg() {
        return rateAvg;
    }

    public void setRateAvg(BigDecimal rateAvg) {
        this.rateAvg = rateAvg;
    }

    public Set<Rate> getRates() {
        return rates;
    }

    public void setRates(Set<Rate> rates) {
        this.rates = rates;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
