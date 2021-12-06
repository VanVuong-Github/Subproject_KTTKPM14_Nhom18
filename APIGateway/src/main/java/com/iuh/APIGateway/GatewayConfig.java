package com.iuh.APIGateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes().route("auth", r -> r.path("/auth/**").filters(f -> f.filter(filter)).uri("lb://AUTH-SERVICE"))
                .route("cart", r -> r.path("/cart/**").filters(f -> f.filter(filter)).uri("lb://CART-SERVICE"))
                .route("users", r -> r.path("/users/**").filters(f -> f.filter(filter)).uri("lb://USER-SERVICE")).build();
    }

}