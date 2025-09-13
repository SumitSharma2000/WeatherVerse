import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { faThumbsUp } from '@fortawesome/free-solid-svg-icons';
import { faThumbsDown } from '@fortawesome/free-solid-svg-icons';
import { faFaceSmile } from '@fortawesome/free-solid-svg-icons';
import { faFaceFrown } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { WeatherService } from '../services/weather.service';


@Component({
  selector: 'app-right-container',
  imports: [CommonModule, FontAwesomeModule],
  templateUrl: './right-container.html',
  styleUrl: './right-container.css'
})
export class RightContainer implements OnInit {
  constructor(
    public weatherService: WeatherService, 
  ){};

  //fa icons for tumbs up/down and smile/frown
  faThumbsUp:any = faThumbsUp;
  faThumbsDown:any = faThumbsDown;
  faFaceSmile:any = faFaceSmile;
  faFaceFrown:any = faFaceFrown;

  
  
  activeTab: string = 'today';
underlineStyle: any = {};

ngOnInit() {
  this.updateUnderline();
}

onTodayClick() {
  this.activeTab = 'today';
  this.updateUnderline();
}

onWeekClick() {
  this.activeTab = 'week';
  this.updateUnderline();
}

updateUnderline() {
  if (this.activeTab === 'today') {
    this.underlineStyle = {
      left: '0%',
      width: '50%'
    };
  } else {
    this.underlineStyle = {
      left: '50%',
      width: '50%'
    };
  }
}


  //functions to control metric values

  //function for click of metric celsius
  onCelsiusClick(){
    this.weatherService.toggleUnit(true);
  }

  //function for click of metric Fahrenheit
  public onFahrenheitClick(){
    this.weatherService.toggleUnit(false);
  }

  // Status indicator methods with emojis
  public getUVStatus(uvIndex: number): string {
    if (uvIndex <= 2) return '😊 Low';
    if (uvIndex <= 5) return '😐 Moderate';
    if (uvIndex <= 7) return '😰 High';
    if (uvIndex <= 10) return '🔥 Very High';
    return '☠️ Extreme';
  }

  public getWindStatus(windSpeed: number): string {
    if (windSpeed <= 5) return '😴 Calm';
    if (windSpeed <= 15) return '🍃 Light';
    if (windSpeed <= 30) return '💨 Moderate';
    if (windSpeed <= 50) return '🌪️ Strong';
    return '⚡ Extreme';
  }

  public getHumidityStatus(humidity: number): string {
    if (humidity <= 30) return '🏜️ Dry';
    if (humidity <= 60) return '😊 Comfortable';
    if (humidity <= 80) return '💧 Humid';
    return '🌊 Very Humid';
  }

  public getVisibilityStatus(visibility: number): string {
    if (visibility >= 10) return '👀 Excellent';
    if (visibility >= 5) return '😊 Good';
    if (visibility >= 2) return '😐 Moderate';
    return '😵 Poor';
  }

  public getAirQualityStatus(aqi: number): string {
    if (aqi <= 50) return '😊 Good';
    if (aqi <= 100) return '😐 Moderate';
    if (aqi <= 150) return '😷 Unhealthy for Sensitive';
    if (aqi <= 200) return '😨 Unhealthy';
    if (aqi <= 300) return '🤢 Very Unhealthy';
    return '☠️ Hazardous';
  }
}
