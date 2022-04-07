package com.leonardo.animepoll.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data

@Component
@ConfigurationProperties(prefix = "jikan")
public class JikanConfig {
    
    private String baseUrl;

}
