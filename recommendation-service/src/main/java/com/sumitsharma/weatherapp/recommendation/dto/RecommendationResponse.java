package com.sumitsharma.weatherapp.recommendation.dto;


public class RecommendationResponse {
	private String city;
    private String condition;
    private double temperature;
    private String recommendation;
    
    public RecommendationResponse(WeatherData data, String recommendation) {
        this.city = data.getCity();
        this.temperature = data.getTemperature();
        this.condition = data.getCondition();
        this.recommendation = recommendation;
    }

	// Getters and setters
    public String getCity() { return city; }
    public String getCondition() { return condition; }
    public double getTemperature() { return temperature; }
    public String getRecommendation() { return recommendation; }
    
    public void setCity(String city) { this.city = city; }
    public void setCondition(String condition) { this.condition = condition; }
    public void setTemperature(double temperature) { this.temperature = temperature; }
    public void setRecommendation(String recommendation) { this.recommendation = recommendation; }
}
