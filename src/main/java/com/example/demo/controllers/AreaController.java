package com.example.demo.controllers;

import com.example.demo.models.DTOs.AreaDTO;
import com.example.demo.models.DTOs.FamilyDTO;
import com.example.demo.services.AreaServices;
import com.example.demo.services.FamilyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/area")
public class AreaController {
    @Autowired
    private AreaServices areaServices;
    @PostMapping("add_area")
    public boolean addArea(@RequestBody AreaDTO areaDTO){
        return areaServices.addArea(areaDTO);
    }
}
