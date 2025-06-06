package com.sumitsharma.weatherapp.recommendation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sumitsharma.weatherapp.recommendation.dto.WeatherData;
import com.sumitsharma.weatherapp.recommendation.service.AIRecommendationService;
import com.sumitsharma.weatherapp.recommendation.service.AlertService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/weather")
@CrossOrigin("*")
public class RecommendationController {

	@Autowired
	private AIRecommendationService recommendationService;

	@Autowired
	private AlertService alertService;

	@PostMapping("/generate")
	public ResponseEntity<String> getRecommendation(@RequestBody WeatherData data) {
		String recommendation = recommendationService.getRecommendation(data);
		return ResponseEntity.ok(recommendation);
	}

	@GetMapping("/recommendation")
	public ResponseEntity<Map<String, Object>> getWeatherRecommendation(@RequestParam String city) {
		// Mock weather data, since there's no integration with weather API
		WeatherData data = new WeatherData(city, 28.5, "Clear", 45, 12.3, 5.6, 2.3); // Add sample uvIndex,
																						// precipitation

		String recommendation = recommendationService.getRecommendation(data);
		String alert = alertService.generateAlert(data.getCondition(), data.getTemperature(), data.getWindSpeed());

		Map<String, Object> response = new HashMap<>();
		response.put("city", data.getCity());
		response.put("condition", data.getCondition());
		response.put("temperature", data.getTemperature());
		response.put("recommendation", recommendation);
		response.put("humidity", data.getHumidity());
		response.put("windSpeed", data.getWindSpeed());
		response.put("uvIndex", data.getUvIndex());
		response.put("precipitation", data.getPrecipitation());
		response.put("alert", alert);

		return ResponseEntity.ok(response);
	}
}
// This controller handles requests for weather recommendations and alerts.
