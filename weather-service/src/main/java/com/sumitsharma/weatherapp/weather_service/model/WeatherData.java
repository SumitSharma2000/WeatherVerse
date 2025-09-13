package com.sumitsharma.weatherapp.weather_service.model;

public class WeatherData {
    private String city;
    private double temperature;
    private String condition;
    private int humidity;
    private double windSpeed;
    private int uvIndex; // UV index
    private String sunrise; // Sunrise time
    private String sunset; // Sunset time
    private double visibilityKm; // <-- change to double
    private double airQualityIndex; // <-- change to double
    private double rainPercent; // <-- add this
    private String iconUrl;
    private String cityImageUrl;

    public WeatherData() {
    }

    // Full constructor
    public WeatherData(String city, double temperature, String condition, int humidity, double windSpeed,
            int uvIndex, String sunrise, String sunset, double visibilityKm, double airQualityIndex, double rainPercent,
            String iconUrl, String cityImageUrl) {
        this.city = city;
        this.temperature = temperature;
        this.condition = condition;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.uvIndex = uvIndex;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.visibilityKm = visibilityKm;
        this.airQualityIndex = airQualityIndex;
        this.rainPercent = rainPercent;
        this.iconUrl = iconUrl;
        this.cityImageUrl = cityImageUrl;
    }

    // Getters and Setters
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(int uvIndex) {
        this.uvIndex = uvIndex;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public double getVisibilityKm() {
        return visibilityKm;
    }

    public void setVisibilityKm(Double visibilityKm) {
        this.visibilityKm = visibilityKm;
    }

    public Double getAirQualityIndex() {
        return airQualityIndex;
    }

    public void setAirQualityIndex(Double airQualityIndex) {
        this.airQualityIndex = airQualityIndex;
    }

    public double getRainPercent() {
        return rainPercent;
    }

    public void setRainPercent(double rainPercent) {
        this.rainPercent = rainPercent;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getCityImageUrl() {
        return cityImageUrl;
    }

    public void setCityImageUrl(String cityImageUrl) {
        this.cityImageUrl = cityImageUrl;
    }
}
