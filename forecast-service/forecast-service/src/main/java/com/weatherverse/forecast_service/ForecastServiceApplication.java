package com.weatherverse.forecast_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ForecastServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForecastServiceApplication.class, args);
	}

}

@RestController
class ForecastController {
    @Value("${weatherapi.url}")
    private String apiUrl;
    @Value("${weatherapi.key}")
    private String apiKey;

    private final RestTemplate http = new RestTemplate();
    @GetMapping("/")
    public String root() { return "ok"; }

    @GetMapping("/api/forecast/health")
    public String health() { return "ok"; }

    @GetMapping("/api/forecast/daily")
    public Object daily(@RequestParam double lat, @RequestParam double lon) {
        String url = apiUrl + "/forecast.json?key=" + apiKey + "&q=" + lat + "," + lon + "&days=7";
        return http.getForObject(url, Object.class);
    }
}
