export interface CurrentWeather {
  location: string;
  temperatureC: number;
  description: string;
  iconUrl: string;
  humidityPercent: number;
  windSpeedKmh: number;
  day: string;
  time: string;
  date?: string;
  rainPercent: number;
}

export interface HourlyForecastItem {
  time: string;
  temperatureC: number;
  iconUrl: string;
}

export interface DailyForecastItem {
  date: string;
  day: string;
  minTempC: number;
  maxTempC: number;
  iconUrl: string;
}

export interface TodayHighlight {
  uvIndex: number ;
  windStatusKmh: number;
  sunrise: string;
  sunset: string;
  humidityPercent: number;
  visibilityKm: number ;
  airQualityIndex: number;
  cityImageUrl?: string;
}
