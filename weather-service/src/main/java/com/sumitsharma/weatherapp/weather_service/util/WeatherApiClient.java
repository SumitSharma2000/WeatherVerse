package com.sumitsharma.weatherapp.weather_service.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sumitsharma.weatherapp.weather_service.model.CurrentWeatherResponse;
import com.sumitsharma.weatherapp.weather_service.model.ForecastResponse;

@Component
public class WeatherApiClient {

    @Value("${weatherapi.url}")
    private String apiUrl;

    @Value("${weatherapi.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public CurrentWeatherResponse getCurrentWeather(String city) {
        String url = apiUrl + "/current.json?key=" + apiKey + "&q=" + city;
        return restTemplate.getForObject(url, CurrentWeatherResponse.class);
    }

    public ForecastResponse get7DaysForecast(String city) {
        String url = apiUrl + "/forecast.json?key=" + apiKey + "&q=" + city + "&days=7";
        return restTemplate.getForObject(url, ForecastResponse.class);
    }
}
