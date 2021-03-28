package com.store.agdemo.service.impl;

import com.store.agdemo.entity.Product;
import com.store.agdemo.filter.ProductFilter;
import com.store.agdemo.repository.CategoryRepository;
import com.store.agdemo.repository.ProductRepository;
import com.store.agdemo.repository.RateRepository;
import com.store.agdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.*;

@Service(ProductService.BEAN_NAME)
public class ProductServiceImpl implements ProductService {
    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private  CategoryRepository categoryRepository;

    @Autowired
    private EntityManager em;

    @Override
    public List<Product> findByCriteria(ProductFilter filter) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);

        Root<Product> root = cq.from(Product.class);
        List<Predicate> predicates = new ArrayList<>();

        if(filter.getCategory() != null) {
            predicates.add(cb.equal(root.get("category"), filter.getCategory()));
        }
        if(filter.getName() != null  && !filter.getName().isEmpty()) {
            predicates.add(cb.like(root.get("title"), "%" + filter.getName() + "%"));
        }
        if(filter.getToPrice() != null){
            predicates.add(cb.gt(root.get("price"), filter.getFromPrice()));
        }
        if(filter.getFromPrice() != null){
            predicates.add(cb.lt(root.get("price"), filter.getToPrice()));
        }
        if(filter.getToRate() != null){
            predicates.add(cb.gt(root.get("rateAvg"), filter.getToRate()));
        }
        if(filter.getFromRate() != null){
            predicates.add(cb.lt(root.get("rateAvg"), filter.getFromRate()));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Product> query = em.createQuery(cq);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Page<Product> getAll(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return new PageImpl<>(products.getContent(), pageable, products.getTotalElements());
    }

    @Override
    public void update(Product product) {
        Optional<Product> optionalProduct = productRepository.findById(product.getId());
        if (optionalProduct.isPresent()) {
            Product entity = optionalProduct.get();
            entity.setTitle(product.getTitle());
            entity.setPrice(product.getPrice());
            entity.setDescription(product.getDescription());
            entity.setCategory(product.getCategory());
            productRepository.save(entity);
        }
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
