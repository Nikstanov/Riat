package com.riat.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
public class GatewayConfig {

    static final String API = "/api/v1";

    @Bean
    RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("getItems",
                        route -> route.path(API+"/items/get")
                                .and()
                                .method(HttpMethod.GET)
//                                .filters(filter -> filter.stripPrefix(1)
//                                )
                                .uri("lb://inventory"))
                .route("postItems",
                        route -> route.path(API+ "/items/add")
                                .and()
                                .method(HttpMethod.POST)
//                                .filters(filter -> filter.stripPrefix(1)
//                                )
                                .uri("lb://inventory"))
                .build();
    }
}
