package com.sumitsharma.weatherapp.weather_service.model;

import java.util.List;

public class ForecastResponse {
    private Forecast forecast;

    public Forecast getForecast() { return forecast; }
    public void setForecast(Forecast forecast) { this.forecast = forecast; }

    public static class Forecast {
        private List<ForecastDayResponse> forecastday;

        public List<ForecastDayResponse> getForecastday() { return forecastday; }
        public void setForecastday(List<ForecastDayResponse> forecastday) { this.forecastday = forecastday; }
    }
}
