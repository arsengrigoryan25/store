package com.store.agdemo.controller;

import com.store.agdemo.entity.Product;
import com.store.agdemo.entity.Rate;
import com.store.agdemo.entity.Users;
import com.store.agdemo.exception.StoreEntityNotFoundException;
import com.store.agdemo.model.RateModel;
import com.store.agdemo.service.ProductService;
import com.store.agdemo.service.RateService;
import com.store.agdemo.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/rate")
@Validated
public class RateController {

    static final Logger log = Logger.getLogger(RateController.class);

    @Autowired
    private RateService rateService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    /**
     * @param rateModel  - the rate which need create
     * @return  -  the created rate
     *
     */
    @PostMapping("/auth")
    public RateModel create(@Valid @RequestBody RateModel rateModel) {
        log.info("Start to creat rate: " + rateModel.toString());
        Rate rate = rateService.create(convertToEntity(rateModel));
        RateModel model = convertToModel(rate);
        log.info("Start to creat rate: " + model.toString());
        return model;
    }

    private Rate convertToEntity(RateModel rateModel){
        Rate rate = new Rate();
        rate.setRate(rateModel.rate);
        rate.setComment(rateModel.comment);
        rate.setCreated(LocalDate.now());
        Product product = productService.getById(rateModel.productId).orElseThrow(() -> new StoreEntityNotFoundException(rateModel.productId, "Product"));
        rate.setProduct(product);
        Users user = userService.getById(rateModel.userId).orElseThrow(() -> new StoreEntityNotFoundException(rateModel.productId, "User"));
        rate.setUser(user);

        return rate;
    }

    private RateModel convertToModel(Rate rate){
        RateModel rateModel = new RateModel();
        rateModel.rate = rate.getRate();
        rateModel.comment = rate.getComment();
        rateModel.productId = rate.getProduct().getId();
        rateModel.userId = rate.getUser().getId();
        return rateModel;
    }
}
