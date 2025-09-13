package com.sumitsharma.weatherapp.weather_service.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PexelsService {

    @Value("${pexels.api.key:}")
    private String pexelsApiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String getCityImageUrl(String cityName) {
        try {
            // Fallback image if API key is not configured
            if (pexelsApiKey == null || pexelsApiKey.isEmpty()) {
                return "https://images.pexels.com/photos/1519088/pexels-photo-1519088.jpeg?auto=compress&cs=tinysrgb&w=800&h=600&fit=crop";
            }

            String url = "https://api.pexels.com/v1/search?query=" + cityName + " city skyline&per_page=1&orientation=landscape";
            
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", pexelsApiKey);
            
            HttpEntity<String> entity = new HttpEntity<>(headers);
            
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                JsonNode jsonNode = objectMapper.readTree(response.getBody());
                JsonNode photos = jsonNode.get("photos");
                
                if (photos != null && photos.isArray() && photos.size() > 0) {
                    JsonNode firstPhoto = photos.get(0);
                    JsonNode src = firstPhoto.get("src");
                    if (src != null && src.get("large") != null) {
                        return src.get("large").asText();
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error fetching image from Pexels: " + e.getMessage());
        }
        
        // Fallback to a default city image
        return "https://images.pexels.com/photos/1519088/pexels-photo-1519088.jpeg?auto=compress&cs=tinysrgb&w=800&h=600&fit=crop";
    }
}
