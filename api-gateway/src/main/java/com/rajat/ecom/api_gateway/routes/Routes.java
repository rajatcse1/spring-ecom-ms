package com.rajat.ecom.api_gateway.routes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Routes {

    @Value("${product.service.url}")
    private String productServiceUrl;


    @Bean
    public RouteLocator productRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("product-service", r -> r.path("/products/**")
                    .filters(f -> f.rewritePath("/products/(?<segment>.*)", "/${segment}"))
                    .uri(productServiceUrl))
                .build();
    }
    
}
