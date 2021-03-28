package com.store.agdemo.controller;

import com.store.agdemo.entity.Category;
import com.store.agdemo.entity.Product;
import com.store.agdemo.entity.User;
import com.store.agdemo.exception.StoreEntityNotFoundException;
import com.store.agdemo.filter.ProductFilter;
import com.store.agdemo.model.ProductModel;
import com.store.agdemo.model.UserModel;
import com.store.agdemo.service.CategoryService;
import com.store.agdemo.service.ProductService;
import com.store.agdemo.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/product")
@Validated
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private RateService rateService;
    @Autowired
    private CategoryService categoryService;

    /**
     * @param productModel - the product which need created
     * @return  - the created product
     *
     * @throws com.store.agdemo.exception.StoreEntityNotFoundException - in case of this product not store
     */
    @PostMapping("/auth")
    @PreAuthorize("hasRole('ADMIN')")
    public ProductModel create(@Valid @RequestBody ProductModel productModel){
        Category category = categoryService.getById(productModel.categoryId).orElseThrow(()-> new StoreEntityNotFoundException(productModel.categoryId, "Category"));
        Product product = convertToEntity(productModel);
        product.setCategory(category);
        Product productToSave = productService.create(product);
        return convertToModel(productToSave);
    }

    /**
     * Get All products by page default number page 0, size 10
     *
     * @param page - the page which to returned
     * @param size - the size of each pages
     * @return -  page of products
     */
    @GetMapping
    public Page<ProductModel> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Product> products = productService.getAll(pageable);
        return new PageImpl<>(products.getContent().stream().map(this::convertToModel).collect(Collectors.toList()),
                pageable, products.getTotalElements());
    }

    /**
     * @param filter - the filter which may search product
     * @param page - the page which to returned
     * @param size - the size of each pages
     * @return -  page of products
     */
    @PostMapping("/filter")
    public Page<ProductModel> getByFilter(@Valid @RequestBody ProductFilter filter,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size){
        List<Product> byCriteria = productService.findByCriteria(filter);
        List<ProductModel> collect = byCriteria.stream().map(this::convertToModel).collect(Collectors.toList());

        PageRequest pageable = PageRequest.of(page, size);
        final int start = (int)pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), byCriteria.size());
        return new PageImpl<>(collect.subList(start, end), pageable, byCriteria.size());
    }

    /**
     * @param id - the id of product which need get
     * @return - a product
     *
     * @throws com.store.agdemo.exception.StoreEntityNotFoundException - in case of this product not store
     */
    @GetMapping("/{id}")
    public ProductModel getById(@PathVariable Long id){
        Product product = productService.getById(id).orElseThrow(()-> new StoreEntityNotFoundException(id, "Product"));
        return convertToModel(product);
    }

    /**
     * @param productModel - the product which need to update
     *
     * @throws com.store.agdemo.exception.StoreEntityNotFoundException - in case of this product not store
     */
    @PatchMapping("/auth")
    @PreAuthorize("hasRole('ADMIN')")
    public void update(@Valid @RequestBody ProductModel productModel){
        Category category = categoryService.getById(productModel.categoryId).orElseThrow(() -> new StoreEntityNotFoundException(productModel.categoryId, "Category"));
        Product product = convertToEntity(productModel);
        product.setCategory(category);
        productService.update(product);
    }

    /**
     * @param id - the id of product which need deleted
     *
     * @throws com.store.agdemo.exception.StoreEntityNotFoundException - in case of this product not store
     */
    @DeleteMapping("/auth/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id){
        Product product = productService.getById(id).orElseThrow(() -> new StoreEntityNotFoundException(id, "Product"));
        rateService.deleteByProduct(product);
        productService.delete(id);
    }

    private Product convertToEntity(ProductModel productModel){
        Product product = new Product();
        product.setId(productModel.id);
        product.setPrice(productModel.price);
        product.setDescription(productModel.description);
        product.setTitle(productModel.title);
        return product;
    }

    private ProductModel convertToModel(Product product){
        ProductModel productModel = new ProductModel();
        productModel.id = product.getId();
        productModel.price = product.getPrice();
        productModel.description = product.getDescription();
        productModel.title = product.getTitle();
        productModel.categoryId = product.getCategory().getId();
        return productModel;
    }

}
