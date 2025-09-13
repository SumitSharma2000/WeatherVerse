package com.sumitsharma.weatherapp.weather_service.model;

import java.util.Map;

public class CurrentWeatherResponse {
    private Location location;
    private Current current;

    public Location getLocation() { return location; }
    public void setLocation(Location location) { this.location = location; }

    public Current getCurrent() { return current; }
    public void setCurrent(Current current) { this.current = current; }

    public static class Location {
        private String name;
        private String localtime;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getLocaltime() { return localtime; }
        public void setLocaltime(String localtime) { this.localtime = localtime; }
    }

    public static class Current {
        private double temp_c;
        private double wind_kph;
        private int humidity;
        private Condition condition;
        private int uv;
        private double vis_km;
        private Map<String, Object> air_quality;
        

        public double getTemp_c() { return temp_c; }
        public void setTemp_c(double temp_c) { this.temp_c = temp_c; }

        public double getWind_kph() { return wind_kph; }
        public void setWind_kph(double wind_kph) { this.wind_kph = wind_kph; }

        public int getHumidity() { return humidity; }
        public void setHumidity(int humidity) { this.humidity = humidity; }

        public Condition getCondition() { return condition; }
        public void setCondition(Condition condition) { this.condition = condition; }

        public int getUv() { return uv; }
        public void setUv(int uv) { this.uv = uv; }

        public double getVis_km() { return vis_km; }
        public void setVis_km(double vis_km) { this.vis_km = vis_km; }

        public Map<String, Object> getAir_quality() { return air_quality; }
        public void setAir_quality(Map<String, Object> air_quality) { this.air_quality = air_quality; }
    }

    public static class Condition {
        private String text;
        private String icon;

        public String getText() { return text; }
        public void setText(String text) { this.text = text; }

        public String getIcon() { return icon; }
        public void setIcon(String icon) { this.icon = icon; }
    }
}
