import { Component, signal, OnInit, Inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { LeftContainer } from './left-container/left-container';
import { RightContainer } from './right-container/right-container';
import { WeatherService } from './services/weather.service';

@Component({
  selector: 'app-root',
  imports: [CommonModule, FontAwesomeModule, LeftContainer, RightContainer],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App implements OnInit {
  protected readonly title = signal('weatherapp');

  constructor(private readonly weather: WeatherService, @Inject(PLATFORM_ID) private readonly platformId: Object) {}

  ngOnInit(): void {
    if (isPlatformBrowser(this.platformId)) {
      // Try to use user's current location; falls back to city if denied
      this.weather.requestGeolocationAndRefresh();
    }
  }
}
