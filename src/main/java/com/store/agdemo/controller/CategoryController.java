package com.store.agdemo.controller;

import com.store.agdemo.entity.Category;
import com.store.agdemo.model.CategoryModel;
import com.store.agdemo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/category")
@Validated
public class CategoryController {

    @Autowired
    private CategoryService service;

    /**
     * @return  return all category list
     */
    @GetMapping
    public List<CategoryModel> getAll() {
        List<Category> categories = service.getAll();
        return categories.stream().map(this::convertToModel).collect(Collectors.toList());
    }

    private CategoryModel convertToModel(Category category){
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.id = category.getId();
        categoryModel.name = category.getName();
        categoryModel.parent = category.getParent();
        return categoryModel;
    }
}
