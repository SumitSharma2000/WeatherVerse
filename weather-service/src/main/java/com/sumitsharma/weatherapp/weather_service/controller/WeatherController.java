package com.sumitsharma.weatherapp.weather_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sumitsharma.weatherapp.weather_service.model.ForecastDayResponse;
import com.sumitsharma.weatherapp.weather_service.model.WeatherData;
import com.sumitsharma.weatherapp.weather_service.service.WeatherService;

@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    /** ✅ Current weather endpoint */
    @GetMapping("/api/weather/current")
    public WeatherData getCurrentWeather(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) Double lat,
            @RequestParam(required = false) Double lon) {
        if (city != null) {
            return weatherService.getWeatherData(city);
        } else if (lat != null && lon != null) {
            return weatherService.getWeatherDataByCoords(lat, lon);
        } else {
            throw new IllegalArgumentException("Either city or both lat and lon must be provided");
        }
    }

    /** ✅ 7-day forecast endpoint */
    @GetMapping("/api/weather/7daysforecast")
    public List<ForecastDayResponse> get7DaysForecast(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) Double lat,
            @RequestParam(required = false) Double lon) {
        if (city != null) {
            return weatherService.get7DaysForecast(city);
        } else if (lat != null && lon != null) {
            return weatherService.get7DaysForecastByCoords(lat, lon);
        } else {
            throw new IllegalArgumentException("Either city or both lat and lon must be provided");
        }
    }
}
