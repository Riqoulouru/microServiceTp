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
                        .path("/client-service/**")
                        .uri("lb://client-service")
                )
                .route(p -> p
                        .path("/commande-service/**")
                        .uri("lb://commande-service")
                )
                .route(p -> p
                        .path("/paiement-service/**")
                        .uri("lb://paiement-service")
                )
                .route(p -> p
                        .path("/produit-service/**")
                        .uri("lb://produit-service")
                )
                .build();
    }
}

