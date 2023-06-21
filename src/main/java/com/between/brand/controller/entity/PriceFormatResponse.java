/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.between.brand.controller.entity;

import java.time.LocalDateTime;

/**
 *
 * @author ander
 */
public class PriceFormatResponse {

    private Long brandId;
    private Long productId;
    private Long rateToApply;
    private LocalDateTime applicationDateStart;
    private LocalDateTime applicationDateEnd;
    private Double finalPrice;

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getRateToApply() {
        return rateToApply;
    }

    public void setRateToApply(Long rateToApply) {
        this.rateToApply = rateToApply;
    }

    public LocalDateTime getApplicationDateStart() {
        return applicationDateStart;
    }

    public void setApplicationDateStart(LocalDateTime aplicationDateStart) {
        this.applicationDateStart = aplicationDateStart;
    }

    public LocalDateTime getApplicationDateEnd() {
        return applicationDateEnd;
    }

    public void setApplicationDateEnd(LocalDateTime aplicationDateEnd) {
        this.applicationDateEnd = aplicationDateEnd;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }
}
