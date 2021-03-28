package com.store.agdemo.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Category extends IdentifiedEntity<Long>{
    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name="parent_id")
    private Category parent;

    @OneToMany(mappedBy = "category")
    private Set<Product> product;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public Set<Product> getProducts() {
        return product;
    }

    public void setProducts(Set<Product> product) {
        this.product = product;
    }
}
