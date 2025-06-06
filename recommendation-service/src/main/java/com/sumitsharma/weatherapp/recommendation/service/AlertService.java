package com.sumitsharma.weatherapp.recommendation.service;

import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class AlertService {

    private static final Map<String, String> conditionAlerts = new LinkedHashMap<>();

    static {

        conditionAlerts.put("storm", "⚠️ Storm alert! Stay indoors and follow local warnings.");
        conditionAlerts.put("flood", "⚠️ Flood warning! Avoid low-lying areas and follow evacuation orders if issued.");
        conditionAlerts.put("snow", "⚠️ Snow alert! Drive carefully and avoid unnecessary travel.");
        conditionAlerts.put("hail", "⚠️ Hail alert! Seek shelter and protect your vehicle.");
        conditionAlerts.put("tornado", "⚠️ Tornado warning! Seek shelter immediately in a sturdy building.");
        conditionAlerts.put("hurricane", "⚠️ Hurricane alert! Evacuate if advised and secure your property.");
        conditionAlerts.put("blizzard", "⚠️ Blizzard warning! Stay indoors and avoid travel.");
        conditionAlerts.put("smoke", "⚠️ Air quality alert! Limit outdoor activities and wear masks if necessary.");
        conditionAlerts.put("fog", "⚠️ Fog alert! Drive with caution and use low beam headlights.");
        conditionAlerts.put("dust storm", "⚠️ Dust storm warning! Stay indoors and avoid outdoor activities.");
        conditionAlerts.put("ash fall", "⚠️ Ash fall alert! Protect your eyes and respiratory system.");
        conditionAlerts.put("uv index", "⚠️ High UV index alert! Wear sunscreen and protective clothing.");
        conditionAlerts.put("heat wave", "⚠️ Heat wave alert! Stay hydrated and avoid strenuous activities outdoors.");
        conditionAlerts.put("cold wave", "⚠️ Cold wave alert! Dress warmly and limit outdoor exposure.");
        conditionAlerts.put("wildfire", "⚠️ Wildfire alert! Follow evacuation orders and stay away from affected areas.");
        conditionAlerts.put("earthquake", "⚠️ Earthquake alert! Drop, cover, and hold on until shaking stops.");
        conditionAlerts.put("air quality", "⚠️ Poor air quality alert! Limit outdoor activities and use air purifiers indoors.");
    }

    public String generateAlert(String condition, double temp, double windSpeed) {
        String lowerCaseCondition = condition.toLowerCase();

        if (windSpeed > 30)
            return "⚠️ High winds today. Secure loose objects and avoid risky outdoor activities.";
        if (temp >= 40)
            return "⚠️ Heatwave alert! Limit outdoor exposure and stay hydrated.";
        if (temp <= 0)
            return "⚠️ Extreme cold alert! Dress warmly and avoid prolonged exposure.";

        for (Map.Entry<String, String> entry : conditionAlerts.entrySet()) {
            if (lowerCaseCondition.contains(entry.getKey())) {
                return entry.getValue();
            }
        }

        return "No severe alerts at the moment.";
    }
}
