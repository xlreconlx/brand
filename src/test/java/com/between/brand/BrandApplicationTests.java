package com.between.brand;

import com.between.brand.controller.PriceController;
import com.between.brand.controller.entity.PriceRequest;
import com.between.brand.controller.entity.PriceFormatResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class BrandApplicationTests {

    @Autowired
    private PriceController priceController;

    @ParameterizedTest
    @CsvFileSource(resources = "/testData.csv", numLinesToSkip = 1) // se cargan los datos de prueba desde un archivo de csv
    void restIntegrationTest(ArgumentsAccessor argumentsAccessor) {
        PriceRequest priceRequest = new PriceRequest(argumentsAccessor.getLong(0), argumentsAccessor.getLong(1), argumentsAccessor.getString(2));
        ResponseEntity<PriceFormatResponse> priceResponse = priceController.getPrice(priceRequest);
        
        if (priceResponse.getStatusCode() == HttpStatus.OK) {
            PriceFormatResponse price = priceResponse.getBody();
            if (price != null) {
                assertEquals(price.getBrandId(), argumentsAccessor.getLong(0)); // get Brand ID
                assertEquals(price.getProductId(), argumentsAccessor.getLong(1)); // get Product ID
                LocalDateTime applicationDate = LocalDateTime.parse(priceRequest.getApplicationDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss"));
                assertTrue(applicationDate.isAfter(price.getApplicationDateStart()) && applicationDate.isBefore(price.getApplicationDateEnd())); // date range
                assertEquals(price.getFinalPrice(), argumentsAccessor.getDouble(3)); // get Final Price
                assertEquals(price.getRateToApply(), argumentsAccessor.getLong(4)); // get  Price List
            }
        } else {
            System.err.println(priceResponse.getStatusCode());
        }
    }

}
