package com.weatherverse.user_preferences_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class UserPreferencesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserPreferencesServiceApplication.class, args);
	}

}

@RestController
class PrefsController {
    @GetMapping("/")
    public String root() { return "ok"; }

    @GetMapping("/api/prefs/health")
    public String health() { return "ok"; }
}
