package com.sumitsharma.weatherapp.weather_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/")
    public String home() {
        return "weather.html";  // static folder mein index.html hona chahiye
    }
}
