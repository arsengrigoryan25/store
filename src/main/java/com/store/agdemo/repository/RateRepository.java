package com.store.agdemo.repository;

import com.store.agdemo.entity.Product;
import com.store.agdemo.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {

    @Query(" select avg(p.rate) from Rate p " +
            " where p.product.id = :productId")
    BigDecimal countRateByProductId(@Param("productId") Long productId);

    void deleteRateByProduct(Product product);
}
