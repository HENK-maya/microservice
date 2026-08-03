package com.hmcode.ecom_order_service.clients.config;

import feign.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.UUID;

@Configuration
public class InventoryFeignClientConfig {

    @Bean
    public Logger.Level inventoryFeignLoggerLevel(){
        return Logger.Level.FULL;
    }
    //depricatd
    /*@Bean
    public Request.Options options(){
        return new Request.Options(3000,5000);
    }*/

    @Bean
    public Request.Options options(){
        return new Request.Options(Duration.ofMillis(3000),Duration.ofMillis(5000), true);
    }

    //retry option
    @Bean
    public Retryer retryer(){
        return new Retryer.Default(1L,3L,3);
    }

    //adding attributes to header like co-relation Id  and authentication token
    @Bean
    public RequestInterceptor requestInterceptor(){
        return requestTemplate -> {
            requestTemplate.header("x-Corelation-Id", UUID.randomUUID().toString());
        };
    }
}
