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
                        .path("/clients/**")
                        .uri("lb://CLIENT")
                )
                .route(p -> p
                        .path("/commandes/**")
                        .uri("lb://COMMANDE")
                )
                .route(p -> p
                        .path("/paiements/**")
                        .uri("lb://PAIEMENT")
                )
                .route(p -> p
                        .path("/produits/**")
                        .uri("lb://PRODUIT")
                )
                .build();
    }
}

