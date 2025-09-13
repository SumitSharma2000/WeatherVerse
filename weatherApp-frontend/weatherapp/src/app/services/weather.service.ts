import { Injectable, Inject, PLATFORM_ID, signal } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { firstValueFrom } from 'rxjs';


import {
  CurrentWeather,
  DailyForecastItem,
  TodayHighlight,
  HourlyForecastItem,
} from '../models/weather.models';
import { ApiService } from './api.service';


@Injectable({ providedIn: 'root' })
export class WeatherService {
  cityName = signal<string>('New York');
  unitCelsius = signal<boolean>(true);
  coords = signal<{ lat: number; lon: number } | null>(null);

  loading = signal<boolean>(false);
  error = signal<string | null>(null);

  current = signal<CurrentWeather | null>(null);
  daily = signal<DailyForecastItem[]>([]);
  hourly = signal<HourlyForecastItem[]>([]); // ✅ Added hourly signal
  highlight = signal<TodayHighlight | null>(null);

  constructor(
    @Inject(PLATFORM_ID) private readonly platformId: Object,
    private readonly api: ApiService
  ) {}

  // ✅ Celsius → Fahrenheit
  toFahrenheit(celsius: number): number {
    return Math.round((celsius * 9) / 5 + 32);
  }

  // ✅ Toggle between °C and °F
  toggleUnit(isCelsius: boolean): void {
    this.unitCelsius.set(isCelsius);
  }

  // ✅ Request geolocation and refresh
  requestGeolocationAndRefresh(): void {
    if (!isPlatformBrowser(this.platformId)) return;

    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        (pos) => {
          this.coords.set({ lat: pos.coords.latitude, lon: pos.coords.longitude });
          this.fetchAll();
        },
        () => {
          this.error.set('Could not fetch your location.');
          this.fetchAll(); // fallback to default city
        }
      );
    } else {
      this.fetchAll(); // fallback
    }
  }

  private mapWeather(
    currentRaw: any,
    dailyRaw: any[]
  ): {
    current: CurrentWeather | null;
    daily: DailyForecastItem[];
    hourly: HourlyForecastItem[];
    highlight: TodayHighlight | null;
  } {
    const loc = currentRaw;
    const d = new Date();

    const mappedCurrent: CurrentWeather = {
      location: loc.city,
      temperatureC: Math.round(loc.temperature),
      description: loc.condition,
      iconUrl: loc.iconUrl?.startsWith('//') ? `https:${loc.iconUrl}` : loc.iconUrl ?? '',
      humidityPercent: loc.humidity,
      windSpeedKmh: loc.windSpeed,
      day: d.toLocaleDateString(undefined, { weekday: 'long' }),
      time: d.toLocaleTimeString(undefined, { hour: '2-digit', minute: '2-digit' }),
      date: d.toLocaleDateString(),
      rainPercent: loc.rainPercent,
    };

    const mappedDaily: DailyForecastItem[] = (dailyRaw || []).map((d: any) => ({
      date: d.date,
      day: new Date(d.date).toLocaleDateString(undefined, { weekday: 'short' }),
      minTempC: Math.round(d.day.mintemp_c ?? 0),
      maxTempC: Math.round(d.day.maxtemp_c ?? 0),
      iconUrl: d.day.condition.icon?.startsWith('//')
        ? `https:${d.day.condition.icon}`
        : d.day.condition.icon ?? '',
    }));

    // ✅ Extract hourly data (WeatherAPI style forecastday[0].hour)
    const mappedHourly: HourlyForecastItem[] = (dailyRaw?.[0]?.hour || []).map((h: any) => ({
      time: h.time.split(' ')[1], // just "HH:mm"
      temperatureC: Math.round(h.temp_c),
      iconUrl: h.condition.icon?.startsWith('//')
        ? `https:${h.condition.icon}`
        : h.condition.icon ?? '',
    }));

    const highlight: TodayHighlight = {
      uvIndex: loc.uvIndex,
      windStatusKmh: loc.windSpeed,
      sunrise: loc.sunrise,
      sunset: loc.sunset,
      humidityPercent: loc.humidity,
      visibilityKm: loc.visibilityKm,
      airQualityIndex: loc.airQualityIndex,
      cityImageUrl: loc.cityImageUrl
    };

    return { current: mappedCurrent, daily: mappedDaily, hourly: mappedHourly, highlight };
  }

  fetchAll(): void {
    if (!isPlatformBrowser(this.platformId)) return;

    const city = this.cityName();
    const coords = this.coords();

    this.loading.set(true);
    this.error.set(null);

    // ✅ Build params without undefined values
    const params: Record<string, string | number | boolean> = {};
    if (coords) {
      params['lat'] = coords.lat;
      params['lon'] = coords.lon;
    } else {
      params['city'] = city;
    }

    Promise.all([
      firstValueFrom(this.api.get<any>(`/api/weather/current`, params)),
      firstValueFrom(this.api.get<any[]>(`/api/weather/7daysforecast`, params)),
    ])
      .then(([currentRaw, dailyRaw]) => {
        const { current, daily, hourly, highlight } = this.mapWeather(currentRaw, dailyRaw);
        this.current.set(current);
        this.daily.set(daily);
        this.hourly.set(hourly);
        this.highlight.set(highlight);
      })
      .catch(() => this.error.set('Failed to load weather data.'))
      .finally(() => this.loading.set(false));
  }

  // ✅ Add method to search by city (clears coordinates)
  searchByCity(cityName: string): void {
    this.cityName.set(cityName);
    this.coords.set(null); // Clear coordinates to force city-based search
    this.fetchAll();
  }
}
