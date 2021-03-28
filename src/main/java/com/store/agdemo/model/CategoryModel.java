package com.store.agdemo.model;

import com.store.agdemo.entity.Category;
import java.io.Serializable;

public class CategoryModel implements Serializable {
    public Long id;
    public String name;
    public Category parent;
}
