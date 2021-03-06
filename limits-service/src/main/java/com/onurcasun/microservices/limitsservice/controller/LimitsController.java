package com.onurcasun.microservices.limitsservice.controller;

import com.onurcasun.microservices.limitsservice.bean.Limits;
import com.onurcasun.microservices.limitsservice.configuration.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    @Autowired
    private Configuration configuration;
    @GetMapping("/limits")
    public Limits retrieveLimits(){        
        return configuration.getLimits();
    }
}
