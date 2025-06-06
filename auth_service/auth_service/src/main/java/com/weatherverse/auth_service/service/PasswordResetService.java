package com.weatherverse.auth_service.service;

import com.weatherverse.auth_service.model.User;
import com.weatherverse.auth_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PasswordResetService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private final Map<String, Long> tokenExpiryStore = new ConcurrentHashMap<>();
    private final Map<String, String> tokenStore = new ConcurrentHashMap<>();
    private static final long EXPIRY_TIME = 30 * 60 * 1000; // 30 mins

    public String generateResetToken(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) return null;
        String token = UUID.randomUUID().toString();
        tokenStore.put(token, email);
        tokenExpiryStore.put(token, System.currentTimeMillis() + EXPIRY_TIME);
        return token;
    }

    public boolean resetPassword(String token, String newPassword) {
        String email = tokenStore.get(token);
        Long expiry = tokenExpiryStore.get(token);
        if (email == null || expiry == null || System.currentTimeMillis() > expiry) return false;
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) return false;
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        tokenStore.remove(token);
        tokenExpiryStore.remove(token);
        return true;
    }

    public String getEmailByToken(String token) {
        return tokenStore.get(token);
    }
}