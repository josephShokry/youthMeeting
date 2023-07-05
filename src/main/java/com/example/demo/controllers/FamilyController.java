package com.example.demo.controllers;

import com.example.demo.models.DTOs.FamilyDTO;
import com.example.demo.models.DTOs.PersonDTO;
import com.example.demo.models.Family;
import com.example.demo.services.FamilyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/family")
public class FamilyController {
    @Autowired
    private FamilyServices familyServices;
    @PostMapping("add_family")
    public boolean addFamily(@RequestBody FamilyDTO familyDTO){
        return familyServices.addFamily(familyDTO);
    }

}
