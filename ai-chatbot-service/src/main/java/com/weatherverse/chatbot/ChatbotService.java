package com.weatherverse.chatbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.json.JSONObject;
import java.util.regex.Pattern;

@Service
public class ChatbotService {

    @Autowired
    private WeatherApiService weatherApiService;

    private final WebClient webClient = WebClient.builder().build();

    public String processQuery(String userMessage) {
        String normalizedMessage = userMessage.toLowerCase();
        
        // Extract location
        String location = extractLocation(normalizedMessage);
        if (location == null) location = "Mumbai"; // default
        
        // Determine query type and generate response
        if (isWeatherQuery(normalizedMessage)) {
            return handleWeatherQuery(normalizedMessage, location);
        } else if (isActivityQuery(normalizedMessage)) {
            return handleActivityQuery(normalizedMessage, location);
        } else {
            return "मैं weather के बारे में सवालों का जवाब दे सकता हूं। कुछ और पूछिए जैसे 'आज दिल्ली में मौसम कैसा है?' या 'running के लिए best time क्या है?'";
        }
    }

    private String extractLocation(String message) {
        String[] cities = {"mumbai", "delhi", "bangalore", "chennai", "kolkata", "hyderabad", "pune", "ahmedabad", "jaipur", "lucknow"};
        for (String city : cities) {
            if (message.contains(city)) {
                return city;
            }
        }
        return null;
    }

    private boolean isWeatherQuery(String message) {
        return message.contains("mausam") || message.contains("weather") || message.contains("barish") || 
               message.contains("dhoop") || message.contains("temperature") || message.contains("garmi") ||
               message.contains("sardi") || message.contains("rain");
    }

    private boolean isActivityQuery(String message) {
        return message.contains("running") || message.contains("exercise") || message.contains("walk") ||
               message.contains("outdoor") || message.contains("best time") || message.contains("suggest");
    }

    private String handleWeatherQuery(String message, String location) {
        try {
            JSONObject weatherData = weatherApiService.getCurrentWeather(location);
            
            if (message.contains("barish") || message.contains("rain")) {
                return generateRainResponse(weatherData, location);
            } else {
                return generateGeneralWeatherResponse(weatherData, location);
            }
        } catch (Exception e) {
            return "Sorry, मैं अभी " + location + " का weather data नहीं ला पा रहा। कुछ देर बाद try करें।";
        }
    }

    private String handleActivityQuery(String message, String location) {
        try {
            JSONObject weatherData = weatherApiService.getCurrentWeather(location);
            return generateActivitySuggestion(weatherData, location);
        } catch (Exception e) {
            return "Sorry, मैं अभी weather data के बिना suggestion नहीं दे पा रहा।";
        }
    }

    private String generateRainResponse(JSONObject weatherData, String location) {
        JSONObject current = weatherData.getJSONObject("current");
        String condition = current.getJSONObject("condition").getString("text").toLowerCase();
        
        if (condition.contains("rain") || condition.contains("drizzle")) {
            return "हां, " + location + " में अभी बारिश हो रही है! ☔ बाहर जाने से पहले umbrella ले जाना।";
        } else if (condition.contains("cloud")) {
            return location + " में अभी clouds हैं, बारिश हो सकती है। 🌥️ Umbrella साथ रखना बेहतर होगा।";
        } else {
            return "नहीं, " + location + " में अभी बारिश नहीं हो रही। मौसम " + condition + " है। ☀️";
        }
    }

    private String generateGeneralWeatherResponse(JSONObject weatherData, String location) {
        JSONObject current = weatherData.getJSONObject("current");
        double temp = current.getDouble("temp_c");
        String condition = current.getJSONObject("condition").getString("text");
        
        String emoji = getWeatherEmoji(condition);
        return location + " में अभी temperature " + temp + "°C है और मौसम " + condition + " है। " + emoji;
    }

    private String generateActivitySuggestion(JSONObject weatherData, String location) {
        JSONObject current = weatherData.getJSONObject("current");
        double temp = current.getDouble("temp_c");
        String condition = current.getJSONObject("condition").getString("text").toLowerCase();
        
        if (temp < 15) {
            return "अभी " + location + " में " + temp + "°C है। Running के लिए perfect time है! 🏃‍♂️ सुबह 7-9 AM या शाम 5-7 PM best होगा।";
        } else if (temp > 30) {
            return location + " में अभी " + temp + "°C है, काफी गर्मी है। 🌡️ Running के लिए सुबह 5-7 AM या रात 8-10 PM का time choose करें।";
        } else if (condition.contains("rain")) {
            return "अभी " + location + " में बारिश हो रही है। ☔ Indoor exercise करना बेहतर होगा या बारिश रुकने का wait करें।";
        } else {
            return location + " में मौसम अच्छा है (" + temp + "°C)! 🌤️ Running के लिए अभी भी अच्छा time है, लेकिन सुबह-शाम और भी बेहतर होगा।";
        }
    }

    private String getWeatherEmoji(String condition) {
        condition = condition.toLowerCase();
        if (condition.contains("sunny") || condition.contains("clear")) return "☀️";
        if (condition.contains("rain")) return "🌧️";
        if (condition.contains("cloud")) return "☁️";
        if (condition.contains("snow")) return "❄️";
        return "🌤️";
    }
}