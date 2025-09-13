package com.sumitsharma.weatherapp.weather_service.service;

import com.sumitsharma.weatherapp.weather_service.model.*;
import com.sumitsharma.weatherapp.weather_service.util.WeatherApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherService {

    @Autowired
    private WeatherApiClient weatherApiClient;

    @Autowired
    private PexelsService pexelsService;

    /** ✅ Weather by city */
    public WeatherData getWeatherData(String city) {
        CurrentWeatherResponse current = weatherApiClient.getCurrentWeather(city);
        ForecastResponse forecast = weatherApiClient.get7DaysForecast(city);

        return buildWeatherData(current, forecast);
    }

    /** ✅ Weather by coordinates */
    public WeatherData getWeatherDataByCoords(Double lat, Double lon) {
        CurrentWeatherResponse current = weatherApiClient.getCurrentWeatherByCoords(lat, lon);
        ForecastResponse forecast = weatherApiClient.get7DaysForecastByCoords(lat, lon);

        return buildWeatherData(current, forecast);
    }

    /** ✅ 7-day forecast by city */
    public List<ForecastDayResponse> get7DaysForecast(String city) {
        ForecastResponse forecast = weatherApiClient.get7DaysForecast(city);
        return forecast.getForecast().getForecastday();
    }

    /** ✅ 7-day forecast by coordinates */
    public List<ForecastDayResponse> get7DaysForecastByCoords(Double lat, Double lon) {
        ForecastResponse forecast = weatherApiClient.get7DaysForecastByCoords(lat, lon);
        return forecast.getForecast().getForecastday();
    }

    /** 🔑 Utility: Map API response into simplified WeatherData */
    private WeatherData buildWeatherData(CurrentWeatherResponse current, ForecastResponse forecast) {
        ForecastDayResponse today = forecast.getForecast().getForecastday().get(0);

        double aqi = 0;
    if (current.getCurrent().getAir_quality() != null &&
        current.getCurrent().getAir_quality().containsKey("pm2_5")) {
        Object pm25 = current.getCurrent().getAir_quality().get("pm2_5");
        if (pm25 != null) {
            aqi = Double.parseDouble(pm25.toString());
        }
    }

    String city = current.getLocation().getName();
    // Get city image from Pexels API
    String cityImageUrl = pexelsService.getCityImageUrl(city);


        return new WeatherData(
                current.getLocation().getName(),
                current.getCurrent().getTemp_c(),
                current.getCurrent().getCondition().getText(),
                current.getCurrent().getHumidity(),
                current.getCurrent().getWind_kph(),
                current.getCurrent().getUv(),
                today.getAstro().getSunrise(),
                today.getAstro().getSunset(),
                current.getCurrent().getVis_km(),
                aqi,
                today.getDay().getDaily_chance_of_rain(),
                current.getCurrent().getCondition().getIcon(), // ✅ Pass icon
                cityImageUrl // ✅ add this
        );
    }
}
