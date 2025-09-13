package com.weatherverse.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@SpringBootApplication
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    // -------------------- Routes --------------------
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            // Weather Service Routes
            .route("weather-current", r -> r.path("/api/weather/current")
                .uri("lb://weather-service"))
            .route("weather-forecast", r -> r.path("/api/weather/7daysforecast")
                .uri("lb://weather-service"))
            // Location Service Routes
            .route("location-search", r -> r.path("/api/location/search")
                .uri("lb://location-service"))
            .route("location-validate", r -> r.path("/api/location/validate")
                .uri("lb://location-service"))
            // Forecast Service Routes
            .route("forecast-daily", r -> r.path("/api/forecast/daily")
                .uri("lb://forecast-service"))
            // User Preferences Service Routes
            .route("user-prefs", r -> r.path("/api/prefs/**")
                .uri("lb://user-preferences-service"))
            .build();
    }

    // -------------------- CORS --------------------
    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();

        // ✅ Only allow your Angular frontend
        corsConfig.addAllowedOrigin("http://localhost:4200");
        corsConfig.addAllowedMethod("*"); // GET, POST, PUT, DELETE, etc.
        corsConfig.addAllowedHeader("*"); // All headers
        corsConfig.setAllowCredentials(true); // allow cookies/auth

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }
}
