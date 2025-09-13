package com.weatherverse.user_preferences_service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrefsController {

    @GetMapping("/")
    public String root() {
        return "ok";
    }

    @GetMapping("/api/prefs/health")
    public String health() {
        return "ok";
    }
}
