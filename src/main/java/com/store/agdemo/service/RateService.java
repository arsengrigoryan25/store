package com.store.agdemo.service;

import com.store.agdemo.entity.Product;
import com.store.agdemo.entity.Rate;

public interface RateService {
    String BEAN_NAME="rateService";

    Rate create(Rate rate);
    void deleteByProduct(Product product);
}
