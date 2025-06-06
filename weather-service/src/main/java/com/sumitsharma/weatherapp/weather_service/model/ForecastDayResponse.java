package com.sumitsharma.weatherapp.weather_service.model;

public class ForecastDayResponse {
    private String date;
    private Day day;

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public Day getDay() { return day; }
    public void setDay(Day day) { this.day = day; }

    public static class Day {
        private double maxtemp_c;
        private double mintemp_c;
        private double avgtemp_c;
        private Condition condition;

        public double getMaxtemp_c() { return maxtemp_c; }
        public void setMaxtemp_c(double maxtemp_c) { this.maxtemp_c = maxtemp_c; }
        public double getMintemp_c() { return mintemp_c; }
        public void setMintemp_c(double mintemp_c) { this.mintemp_c = mintemp_c; }
        public double getAvgtemp_c() { return avgtemp_c; }
        public void setAvgtemp_c(double avgtemp_c) { this.avgtemp_c = avgtemp_c; }
        public Condition getCondition() { return condition; }
        public void setCondition(Condition condition) { this.condition = condition; }
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
