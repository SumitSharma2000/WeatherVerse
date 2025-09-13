package com.sumitsharma.weatherapp.weather_service.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sumitsharma.weatherapp.weather_service.model.CurrentWeatherResponse;
import com.sumitsharma.weatherapp.weather_service.model.ForecastResponse;

@Component
public class WeatherApiClient {

    @Value("${weatherapi.url:https://api.weatherapi.com/v1}")
    private String apiUrl;

    @Value("${weatherapi.key:}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public CurrentWeatherResponse getCurrentWeather(String city) {
        validateApiKey();
        String url = apiUrl + "/current.json?key=" + apiKey + "&q=" + city + "&aqi=yes";
        return restTemplate.getForObject(url, CurrentWeatherResponse.class);
    }

    public CurrentWeatherResponse getCurrentWeatherByCoords(Double lat, Double lon) {
        validateApiKey();
        String url = apiUrl + "/current.json?key=" + apiKey + "&q=" + lat + "," + lon + "&aqi=yes";
        return restTemplate.getForObject(url, CurrentWeatherResponse.class);
    }

    public ForecastResponse get7DaysForecast(String city) {
        validateApiKey();
        String url = apiUrl + "/forecast.json?key=" + apiKey + "&q=" + city + "&days=7" + "&aqi=yes";
        return restTemplate.getForObject(url, ForecastResponse.class);
    }

    public ForecastResponse get7DaysForecastByCoords(Double lat, Double lon) {
        validateApiKey();
        String url = apiUrl + "/forecast.json?key=" + apiKey + "&q=" + lat + "," + lon + "&days=7" + "&aqi=yes";
        return restTemplate.getForObject(url, ForecastResponse.class);
    }

    private void validateApiKey() {
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException("weatherapi.key is not configured. Set it in Config Server or application properties.");
        }
    }
}
