import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';
import { faLocation } from '@fortawesome/free-solid-svg-icons';
import { faCloud } from '@fortawesome/free-solid-svg-icons';
import { faCloudRain } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { WeatherService } from '../services/weather.service';

@Component({
  selector: 'app-left-container',
  imports: [CommonModule, FontAwesomeModule],
  templateUrl: './left-container.html',
  styleUrls: ['./left-container.css']
})
export class LeftContainer {
  // variables for font awesome icons
  
  
  //Variables for left-nav-bar search icons
  faMagnifyingGlass:any = faMagnifyingGlass;
  faLocation:any = faLocation

  // Variables for temperature summary
  faCloud:any = faCloud;
  faCloudRain:any = faCloudRain;
   
  constructor(public weatherService:WeatherService){}
  
  public onSearch(location:string){
    if (location && location.trim()) {
      this.weatherService.searchByCity(location.trim());
    }
  }

  public useMyLocation(){
    this.weatherService.requestGeolocationAndRefresh();
  }

  public getCurrentDateTime(): string {
    const now = new Date();
    return now.toLocaleString('en-US', {
      weekday: 'long',
      year: 'numeric',
      month: 'long',
      day: 'numeric',
      hour: '2-digit',
      minute: '2-digit'
    });
  }

}
