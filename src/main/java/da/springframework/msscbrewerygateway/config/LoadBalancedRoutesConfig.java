package da.springframework.msscbrewerygateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("local-discovery")
@Configuration
public class LoadBalancedRoutesConfig {

    @Bean
    public RouteLocator loadBalancedRoutes(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(predicateSpec -> predicateSpec.path("/api/v1/beer*", "/api/v1/beer/*", "/api/v1/beerUpc/*")
                        .uri("lb://beer-service"))
                .route(predicateSpec -> predicateSpec.path("/api/v1/customers/**")
                        .uri("lb://order-service"))
                .route(predicateSpec -> predicateSpec.path("/api/v1/beer/*/inventory")
                        .uri("lb://inventory-service"))
                .build();
    }
}
