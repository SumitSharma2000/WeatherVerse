package com.weatherverse.chatbot;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.json.JSONObject;

@Service
public class WeatherApiService {

    private final WebClient webClient = WebClient.builder().build();
    private final String API_KEY = "b28e87a48b4a430d93651544250909"; // Replace with actual API key
    private final String BASE_URL = "https://api.weatherapi.com/v1";

    public JSONObject getCurrentWeather(String location) {
        try {
            String response = webClient.get()
                .uri(BASE_URL + "/current.json?key=" + API_KEY + "&q=" + location)
                .retrieve()
                .bodyToMono(String.class)
                .block();
            
            return new JSONObject(response);
        } catch (Exception e) {
            // Fallback mock data for demo
            return createMockWeatherData(location);
        }
    }

    private JSONObject createMockWeatherData(String location) {
        JSONObject mockData = new JSONObject();
        JSONObject current = new JSONObject();
        JSONObject condition = new JSONObject();
        
        // Mock weather data
        current.put("temp_c", 25.0);
        condition.put("text", "Partly cloudy");
        current.put("condition", condition);
        mockData.put("current", current);
        
        return mockData;
    }
}