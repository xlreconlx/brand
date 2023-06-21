/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.between.brand.repository;

import com.between.brand.entity.Price;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ander
 */
@Repository
public interface PriceRepository extends CrudRepository<Price, Long> {

    @Query("SELECT p FROM Price p JOIN p.brand b WHERE b.brandId = :brandId AND p.productId = :productId AND (:aplicationDate BETWEEN p.startDate AND p.endDate) ORDER BY p.priority ASC")
    List<Price> findByBrandProductAndDate(@Param("brandId") Long brandId, @Param("productId") Long productId, @Param("aplicationDate") LocalDateTime aplicationDate);
}
