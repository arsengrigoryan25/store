package com.store.agdemo.service.impl;

import com.store.agdemo.entity.Category;
import com.store.agdemo.repository.CategoryRepository;
import com.store.agdemo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service(CategoryService.BEAN_NAME)
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    @Transactional
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getById(Long id) {
        return categoryRepository.findById(id);
    }


}
