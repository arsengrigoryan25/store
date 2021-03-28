package com.store.agdemo.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Rate extends IdentifiedEntity<Long> {
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="product_id")
    private Product product;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="user_id")
    private User user;

    @Column
    private Integer rate;
    @Column
    private String comment;
    @Column
    private LocalDate created;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }
}
