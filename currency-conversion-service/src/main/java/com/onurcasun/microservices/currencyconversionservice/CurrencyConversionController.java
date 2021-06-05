package com.onurcasun.microservices.currencyconversionservice;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class CurrencyConversionController {

    @Autowired
    Environment environment;
    
    //http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10
    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion getMethodName(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
        HashMap<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        //http://localhost:8000/currency-exchange/from/{from}/to/{to}
        ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate()
            .getForEntity(
                "http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversion.class, 
                uriVariables);

        CurrencyConversion currencyConversion = responseEntity.getBody();
        return new CurrencyConversion(
            currencyConversion.getId(),
            from, 
            to, 
            quantity, 
            currencyConversion.getConversionMultiple(), 
            quantity.multiply(currencyConversion.getConversionMultiple()), 
            currencyConversion.getEnvironment());
    }    
}
