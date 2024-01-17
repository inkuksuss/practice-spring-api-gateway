package com.example.apigatewayservice.config;

import jakarta.ws.rs.GET;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.function.RequestPredicate;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.AfterFilterFunctions.setResponseHeader;
import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.setRequestHeader;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.web.servlet.function.RequestPredicates.path;
import static org.springframework.web.servlet.function.RequestPredicates.pathExtension;

@Configuration
public class FilterConfig {

    @Bean
    public RouterFunction<ServerResponse> gatewayRoutes() {
        return GatewayRouterFunctions.route("gatewayRoutes")
                .route(path("/first-service/**"), http("http://localhost:8081"))
                        .before(setRequestHeader("first-request", "first-request-header"))
                        .after(setResponseHeader("first-response", "first-response-header"))
                .route(path("/second-service/**"), http("http://localhost:8082"))
                    .before(setRequestHeader("second-request", "second-request-header"))
                    .after(setResponseHeader("second-response", "second-response-header"))
                .build();
    }
}
