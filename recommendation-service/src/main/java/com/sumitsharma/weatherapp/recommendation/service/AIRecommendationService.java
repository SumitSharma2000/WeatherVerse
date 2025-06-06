package com.sumitsharma.weatherapp.recommendation.service;

import org.springframework.stereotype.Service;
import com.sumitsharma.weatherapp.recommendation.dto.WeatherData;

@Service
public class AIRecommendationService {

    private boolean useAI = false;

    public String getRecommendation(WeatherData data) {
        return useAI ? generateAIRecommendation(data) : generateRuleBasedRecommendation(data);
    }

    private String generateRuleBasedRecommendation(WeatherData data) {
        String condition = data.getCondition().toLowerCase();
        StringBuilder recommendation = new StringBuilder();

        if (condition.contains("rain")) {
            recommendation.append("It’s raining in ").append(data.getCity())
                .append(" ☔. Carry an umbrella and avoid leather shoes.");
        } else if (data.getTemperature() > 35) {
            recommendation.append("It's quite hot in ").append(data.getCity())
                .append(" 🔥. Stay hydrated and avoid going out in peak hours.");
        } else if (data.getTemperature() < 10) {
            recommendation.append("It's chilly in ").append(data.getCity())
                .append(" 🧥. Wear warm clothes.");
        } else {
            recommendation.append("Weather looks good in ").append(data.getCity())
                .append(" 🌤️. Enjoy your day!");
        }

        return recommendation.toString();
    }

    private String generateAIRecommendation(WeatherData data) {
        return "AI recommendation is under development.";
    }
}