package com.sumitsharma.weatherapp.weather_service.model;

public class CurrentWeatherResponse {
    private Location location;
    private Current current;

    public Location getLocation() { return location; }
    public void setLocation(Location location) { this.location = location; }

    public Current getCurrent() { return current; }
    public void setCurrent(Current current) { this.current = current; }

    public static class Location {
        private String name;
        private String region;
        private String country;
        private String localtime;

        // getters and setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getRegion() { return region; }
        public void setRegion(String region) { this.region = region; }
        public String getCountry() { return country; }
        public void setCountry(String country) { this.country = country; }
        public String getLocaltime() { return localtime; }
        public void setLocaltime(String localtime) { this.localtime = localtime; }
    }

    public static class Current {
        private double temp_c;
        private double wind_kph;
        private int humidity;
        private Condition condition;
        // pressure
        private double pressure_mb;

        // getters and setters
        public double getTemp_c() { return temp_c; }
        public void setTemp_c(double temp_c) { this.temp_c = temp_c; }
        public double getWind_kph() { return wind_kph; }
        public void setWind_kph(double wind_kph) { this.wind_kph = wind_kph; }
        public int getHumidity() { return humidity; }
        public void setHumidity(int humidity) { this.humidity = humidity; }
        public Condition getCondition() { return condition; }
        public void setCondition(Condition condition) { this.condition = condition; }
        public double getPressure_mb() { return pressure_mb; }
        public void setPressure_mb(double pressure_mb) { this.pressure_mb = pressure_mb; }
    }

    public static class Condition {
        private String text;
        private String icon;

        // getters and setters
        public String getText() { return text; }
        public void setText(String text) { this.text = text; }
        public String getIcon() { return icon; }
        public void setIcon(String icon) { this.icon = icon; }
    }
}
