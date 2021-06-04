package com.onurcasun.microservices.limitsservice.configuration;

import com.onurcasun.microservices.limitsservice.bean.Limits;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("limits-service")
public class Configuration {
    
    private Limits limits;

    public Limits getLimits() {
        return limits;
    }

    public void setLimits(Limits limits) {
        this.limits = limits;
    }

}
