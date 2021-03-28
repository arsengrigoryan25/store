package com.store.agdemo.service;

import com.store.agdemo.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    String BEAN_NAME = "categoryService";

    Category create(Category category);
    List<Category> getAll();
    Optional<Category> getById(Long id);

}
