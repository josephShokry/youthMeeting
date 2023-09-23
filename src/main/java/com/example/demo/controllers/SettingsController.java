package com.example.demo.controllers;

import com.example.demo.util.security.EndPoints;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping(EndPoints.SETTINGS)
public class SettingsController {
    @Operation(
            summary = "Use this api to change the language of response messages",
            description = "To change the language of response messages the available languages are ar, en")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "language setting is set correctly")
    })
    @PostMapping(EndPoints.SET_LANGUAGE)
    public void changeLanguage(HttpServletRequest request, @RequestParam String language) {
        request.getSession().setAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE",
                Locale.forLanguageTag(language));
    }
}
