/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.between.brand.service;

import com.between.brand.controller.entity.PriceRequest;
import com.between.brand.controller.entity.PriceFormatResponse;
import com.between.brand.entity.Price;
import com.between.brand.repository.PriceRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ander
 */
@Service
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;

    public PriceFormatResponse getFinalPrice(PriceRequest priceRequest) {
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(priceRequest.getApplicationDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss"));
            List<Price> priceList = priceRepository.findByBrandProductAndDate(priceRequest.getBrandId(), priceRequest.getProductId(), localDateTime);

            if (priceList.isEmpty()) {
                return null;
            }
            Price price = priceList.stream().max(Comparator.comparing(p -> p.getPriority())).get();
            PriceFormatResponse priceFormatResponse = new PriceFormatResponse();
            priceFormatResponse.setProductId(price.getProductId());
            priceFormatResponse.setBrandId(price.getBrand().getBrandId());
            priceFormatResponse.setFinalPrice(price.getPrice());
            priceFormatResponse.setRateToApply(price.getPriceList());
            priceFormatResponse.setApplicationDateStart(price.getStartDate());
            priceFormatResponse.setApplicationDateEnd(price.getEndDate());

            return priceFormatResponse;
        } catch (Exception e) {
            System.out.println("Wrong date format");
            return null;
        }
    }
}
