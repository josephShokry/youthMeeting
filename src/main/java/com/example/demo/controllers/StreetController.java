package com.example.demo.controllers;

import com.example.demo.models.DTOs.AreaDTO;
import com.example.demo.models.DTOs.StreetDTO;
import com.example.demo.services.FamilyServices;
import com.example.demo.services.StreetServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/street")
public class StreetController {
    @Autowired
    private StreetServices streetServices;
    @PostMapping("add_street")
    public boolean addStreet(@RequestBody StreetDTO streetDTO){
        return streetServices.addStreet(streetDTO);
    }
}
