package com.store.agdemo.service.impl;

import com.store.agdemo.entity.Product;
import com.store.agdemo.entity.Rate;
import com.store.agdemo.repository.ProductRepository;
import com.store.agdemo.repository.RateRepository;
import com.store.agdemo.service.ProductService;
import com.store.agdemo.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service(RateService.BEAN_NAME)
public class RateServiceImpl implements RateService {

    @Autowired
    private RateRepository rateRepository;
    @Autowired
    private ProductService productService;

    @Override
    @Transactional
    public Rate create(Rate rate) {
        Rate save = rateRepository.save(rate);
        countAndSetProductAvg(rate.getProduct());
        return save;
    }

    @Override
    public void deleteByProduct(Product product) {
        rateRepository.deleteRateByProduct(product);
    }

    public void countAndSetProductAvg(Product product){
        BigDecimal bigDecimal = rateRepository.countRateByProductId(product.getId());
        product.setRateAvg(bigDecimal);
        productService.update(product);
    }
}
