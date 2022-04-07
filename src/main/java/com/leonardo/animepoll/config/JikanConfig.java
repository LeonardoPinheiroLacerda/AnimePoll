package com.leonardo.animepoll.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter

@Component
@ConfigurationProperties(prefix = "jikan")
public class JikanConfig {
    
    private String baseUrl;

}
