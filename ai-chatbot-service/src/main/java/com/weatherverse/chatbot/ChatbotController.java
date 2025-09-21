package com.weatherverse.chatbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080", "*"})
public class ChatbotController {

    @Autowired
    private ChatbotService chatbotService;

    @PostMapping("/message")
    public Map<String, String> processMessage(@RequestBody Map<String, String> request) {
        String userMessage = request.get("message");
        String language = request.getOrDefault("language", "hi");
        String response = chatbotService.processQuery(userMessage, language);
        return Map.of("response", response);
    }
}