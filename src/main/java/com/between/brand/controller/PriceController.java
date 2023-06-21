/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.between.brand.controller;

import com.between.brand.controller.entity.PriceRequest;
import com.between.brand.controller.entity.PriceFormatResponse;
import com.between.brand.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author ander
 */
@Controller
public class PriceController {
    @Autowired
    private PriceService priceService;

    @RequestMapping(value = "/price" ,method = RequestMethod.POST)
    public ResponseEntity<PriceFormatResponse> getPrice(@RequestBody PriceRequest priceRequest){
        PriceFormatResponse priceFormatResponse = priceService.getFinalPrice(priceRequest);
        
        if(priceFormatResponse != null){
            return ResponseEntity.ok(priceFormatResponse);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
