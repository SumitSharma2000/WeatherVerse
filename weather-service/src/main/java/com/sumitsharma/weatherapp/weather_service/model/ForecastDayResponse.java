package com.sumitsharma.weatherapp.weather_service.model;

public class ForecastDayResponse {
    private String date;
    private Day day;
    private Astro astro;

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public Day getDay() { return day; }
    public void setDay(Day day) { this.day = day; }

    public Astro getAstro() { return astro; }
    public void setAstro(Astro astro) { this.astro = astro; }

    public static class Day {
        private double maxtemp_c;
        private double mintemp_c;
        private int daily_chance_of_rain;
        private Condition condition;

        public double getMaxtemp_c() { return maxtemp_c; }
        public void setMaxtemp_c(double maxtemp_c) { this.maxtemp_c = maxtemp_c; }

        public double getMintemp_c() { return mintemp_c; }
        public void setMintemp_c(double mintemp_c) { this.mintemp_c = mintemp_c; }

        public int getDaily_chance_of_rain() { return daily_chance_of_rain; }
        public void setDaily_chance_of_rain(int daily_chance_of_rain) { this.daily_chance_of_rain = daily_chance_of_rain; }

        public Condition getCondition() { return condition; }
        public void setCondition(Condition condition) { this.condition = condition; }
    }

    public static class Astro {
        private String sunrise;
        private String sunset;

        public String getSunrise() { return sunrise; }
        public void setSunrise(String sunrise) { this.sunrise = sunrise; }

        public String getSunset() { return sunset; }
        public void setSunset(String sunset) { this.sunset = sunset; }
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
