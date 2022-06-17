package da.springframework.msscbrewerygateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

//@Profile("google")
//@Configuration
public class GoogleConfig {

    @Bean
    public RouteLocator googleRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(r -> r.path("/googlesearch2")
                        .filters(gatewayFilterSpec -> gatewayFilterSpec.rewritePath("/googlesearch2(?<segment>/?.*)", "/${segment}"))
                        .uri("https://google.com"))
                .build();
    }
}
