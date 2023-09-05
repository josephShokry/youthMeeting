package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class home {
    @GetMapping()
    public String hi(){
        return "HELLLLLLLLOOOOOOOOOO";
    }
}
