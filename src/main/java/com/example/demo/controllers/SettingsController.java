package com.example.demo.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("/settings")
public class SettingsController {
    @PostMapping("language")
    public void changeLanguage(HttpServletRequest request, @RequestParam String language) {
        request.getSession().setAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE",
                Locale.forLanguageTag(language));
    }
}
