package com.store.agdemo.service;

import com.store.agdemo.entity.Product;
import com.store.agdemo.filter.ProductFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    String BEAN_NAME = "productService";

    List<Product> findByCriteria(ProductFilter filter);

    Product create(Product product);
    Page<Product> getAll(Pageable pageable);
    Optional<Product> getById(Long id);
    void update(Product product);
    void delete(Long id);
}
