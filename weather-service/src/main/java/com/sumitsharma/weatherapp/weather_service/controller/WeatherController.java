package com.sumitsharma.weatherapp.weather_service.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sumitsharma.weatherapp.weather_service.model.CurrentWeatherResponse;
import com.sumitsharma.weatherapp.weather_service.model.ForecastDayResponse;
import com.sumitsharma.weatherapp.weather_service.model.WeatherData;
import com.sumitsharma.weatherapp.weather_service.service.WeatherService;

@RestController
@CrossOrigin("*")
public class WeatherController {

	@Autowired
    private WeatherService weatherService;

    @GetMapping("/api/weather/current")
    public CurrentWeatherResponse getCurrentWeather(@RequestParam String city) {
        return weatherService.getWeatherByCity(city);
    }

    @GetMapping("/api/weather/7daysforecast")
    public List<ForecastDayResponse> get7DaysForecast(@RequestParam String city) {
        return weatherService.get7DaysForecastByCity(city);
    }
    
    // @Autowired
    // private AIRecommendationService recommendationService;

    // @GetMapping("/recommendation")
    // public ResponseEntity<?> getRecommendation(@RequestParam String city) {
    //     CurrentWeatherResponse raw = weatherService.getWeatherByCity(city);
    //     if (raw == null) {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    //             .body("Could not retrieve weather for city: " + city);
    //  }

    //  WeatherData data = weatherService.toWeatherData(raw);
    //  String recommendation = recommendationService.getRecommendation(data);

    //  Map<String, Object> response = new HashMap<>();
    //  response.put("city", data.getCity());
    //  response.put("temperature", data.getTemperature());
    //  response.put("condition", data.getCondition());
    //  response.put("recommendation", recommendation);

    //  return ResponseEntity.ok(response);
    // }
}
