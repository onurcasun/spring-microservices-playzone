package com.onurcasun.microservices.currencyexchangemicroservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
    
    @Autowired
    private CurrencyExchangeRepository repository;

    @Autowired
    private Environment environment;
        
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeRate(@PathVariable String from, @PathVariable String to) {
        
        logger.info("retrieveExchangeRate called with {} to {}", from, to);

        CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);
        if(currencyExchange == null){
            throw new RuntimeException(String.format("Unable to find data for from: {%s}, to: {%s}", from,to));
        }

        String port = getEnvironmentPort();
        currencyExchange.setEnvironment(port);
        repository.findAll();
        
        return currencyExchange;
    }

    private String getEnvironmentPort() {
        String port = environment.getProperty("local.server.port");
        return port;
    }
}
