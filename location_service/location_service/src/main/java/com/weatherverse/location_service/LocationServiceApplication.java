package com.weatherverse.location_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class LocationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocationServiceApplication.class, args);
	}

}

@RestController
class LocationController {
    @GetMapping("/")
    public String root() { return "ok"; }

    @GetMapping("/api/location/health")
    public String health() { return "ok"; }

    @Value("${weatherapi.url}")
    private String apiUrl;
    @Value("${weatherapi.key}")
    private String apiKey;

    private final RestTemplate http = new RestTemplate();

    // City autocomplete/search via WeatherAPI
    @GetMapping("/api/location/search")
    public Object search(@RequestParam String q) {
        String url = apiUrl + "/search.json?key=" + apiKey + "&q=" + q;
        return http.getForObject(url, Object.class);
    }

    // simple echo validate; placeholder
    @GetMapping("/api/location/validate")
    public String validate(@RequestParam double lat, @RequestParam double lon) {
        return "true";
    }
}
