package com.onurcasun.microservices.currencyconversionservice;

import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class CurrencyConversionController {

    @Autowired
    Environment environment;
    
    //http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10
    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion getMethodName(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {

        String port = getEnvironment();       
        return new CurrencyConversion(10L,from, to, BigDecimal.ONE, quantity, BigDecimal.ONE, port);
    }

    private String getEnvironment() {
        return environment.getProperty("local.server.port") + " instance-id";
    }   
    
}
