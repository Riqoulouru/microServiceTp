package LosGuerreros.APIGateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/product-service/**")
                        .uri("lb://product-service")
                )
                .route(p -> p
                        .path("/user-service/**")
                        .uri("lb://user-service")
                )
                .build();
    }
}

