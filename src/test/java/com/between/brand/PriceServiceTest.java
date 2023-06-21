/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.between.brand;

import com.between.brand.controller.entity.PriceFormatResponse;
import com.between.brand.controller.entity.PriceRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author ander
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class PriceServiceTest {
    
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;
    
    @BeforeEach
    public void setUp(){
    }
    
    @Test
    public void getPrice() throws Exception {
        
        MvcResult getPriceResult = mvc.perform(
                MockMvcRequestBuilders.post("/price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new PriceRequest(1L, 35455L, "2020-06-16-21.00.00"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brandId").exists())
                .andExpect(jsonPath("$.productId").exists())
                .andExpect(jsonPath("$.rateToApply").exists())
                .andExpect(jsonPath("$.applicationDateStart").exists())
                .andExpect(jsonPath("$.applicationDateEnd").exists())
                .andExpect(jsonPath("$.finalPrice").exists())
                .andReturn();
        
        PriceFormatResponse priceFormatResponse = objectMapper.readValue(getPriceResult.getResponse().getContentAsString(), PriceFormatResponse.class);
        
        assertEquals(1L, priceFormatResponse.getBrandId());
        assertEquals(35455L, priceFormatResponse.getProductId());
        assertEquals(4L, priceFormatResponse.getRateToApply());
        assertEquals(38.95, priceFormatResponse.getFinalPrice());
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-16-21.00.00", DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss"));
        assertTrue(applicationDate.isAfter(priceFormatResponse.getApplicationDateStart()) && applicationDate.isBefore(priceFormatResponse.getApplicationDateEnd()));

    
    }
    
}
