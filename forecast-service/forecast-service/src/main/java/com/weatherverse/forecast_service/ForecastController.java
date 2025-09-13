package com.weatherverse.forecast_service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/forecast")
public class ForecastController {

    @Value("${weatherapi.url}")
    private String apiUrl;

    @Value("${weatherapi.key}")
    private String apiKey;

    private final RestTemplate http;

    public ForecastController(RestTemplate http) {
        this.http = http;
    }

    @GetMapping("/health")
    public String health() {
        return "ok";
    }

    @GetMapping("/daily")
    public Object daily(@RequestParam double lat, @RequestParam double lon) {
        String url = apiUrl + "/forecast.json?key=" + apiKey + "&q=" + lat + "," + lon + "&days=7";
        return http.getForObject(url, Object.class);
    }
}
