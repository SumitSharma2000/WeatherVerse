package com.weatherverse.location_service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class LocationController {

    private final RestTemplate http;

    public LocationController(RestTemplate http) {
        this.http = http;
    }

    @Value("${weatherapi.url}")
    private String apiUrl;

    @Value("${weatherapi.key}")
    private String apiKey;

    @GetMapping("/")
    public String root() {
        return "ok";
    }

    @GetMapping("/api/location/health")
    public String health() {
        return "ok";
    }

    // City autocomplete/search via WeatherAPI
    @GetMapping("/api/location/search")
    public Object search(@RequestParam String q) {
        String url = apiUrl + "/search.json?key=" + apiKey + "&q=" + q;
        return http.getForObject(url, Object.class);
    }

    // Simple echo validate; placeholder
    @GetMapping("/api/location/validate")
    public String validate(@RequestParam double lat, @RequestParam double lon) {
        return "true";
    }
}
