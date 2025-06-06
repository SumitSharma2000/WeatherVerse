package com.weatherverse.auth_service.controller;

import com.weatherverse.auth_service.service.PasswordResetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ForgotPasswordController {

    @Autowired
    private PasswordResetService passwordResetService;

    @PostMapping("/forgot-password")
    public String processForgotPassword(@RequestParam("email") String email, Model model) {
        String token = passwordResetService.generateResetToken(email);
        if (token == null) {
            model.addAttribute("error", "Email not found.");
            return "forgot-password";
        }
        model.addAttribute("message", "Reset link has been sent.");
        model.addAttribute("token", token);
        return "reset-password";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam("token") String token,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("confirmPassword") String confirmPassword,
            Model model) {
        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match.");
            model.addAttribute("token", token);
            return "reset-password";
        }
        if (!passwordResetService.resetPassword(token, newPassword)) {
            model.addAttribute("error", "Token expired or invalid.");
            return "reset-password";
        }
        return "redirect:/login?resetSuccess";
    }

    @GetMapping("/reset-password")
    public String showResetPasswordPage(@RequestParam("token") String token, Model model) {
        String email = passwordResetService.getEmailByToken(token);
        if (email == null) {
            model.addAttribute("error", "Invalid or expired token.");
            return "forgot-password";
        }
        model.addAttribute("token", token);
        return "reset-password";
    }
}