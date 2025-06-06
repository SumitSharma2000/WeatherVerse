package com.sumitsharma.weatherapp.weather_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sumitsharma.weatherapp.weather_service.model.CurrentWeatherResponse;
import com.sumitsharma.weatherapp.weather_service.model.ForecastDayResponse;
import com.sumitsharma.weatherapp.weather_service.model.ForecastResponse;
import com.sumitsharma.weatherapp.weather_service.model.WeatherData;
import com.sumitsharma.weatherapp.weather_service.util.WeatherApiClient;

@Service
public class WeatherService {

	 @Autowired
	 private WeatherApiClient weatherApiClient;
	 
	 private final RestTemplate restTemplate = new RestTemplate(); // 
	 
	 public CurrentWeatherResponse getWeatherByCity(String city) {
	   return weatherApiClient.getCurrentWeather(city);
	    }

	 public List<ForecastDayResponse> get7DaysForecastByCity(String city) {
	     ForecastResponse forecastResponse = weatherApiClient.get7DaysForecast(city);
	        if (forecastResponse == null || forecastResponse.getForecast() == null) {
	            return List.of();
	        }
	        return forecastResponse.getForecast().getForecastday();
	    }

	 
	 public WeatherData toWeatherData(CurrentWeatherResponse response) {
	        return new WeatherData(
	            response.getLocation().getName(),
	            response.getCurrent().getTemp_c(),
	            response.getCurrent().getCondition().getText(),
	            response.getCurrent().getHumidity(),
	            response.getCurrent().getWind_kph()
	        );
	    }
	 
	 public String getRecommendation(String city) {
	        CurrentWeatherResponse response = getWeatherByCity(city);

	        // convert to WeatherData DTO
	        WeatherData data = toWeatherData(response);

	        // Call recommendation-service
	        String recommendation = restTemplate.postForObject(
	            "http://localhost:8081/api/recommendation/generate",  // adjust if using gateway/service discovery
	            data,
	            String.class
	        );

	        return recommendation;
	    }
}

