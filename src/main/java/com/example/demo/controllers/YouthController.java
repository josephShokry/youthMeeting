package com.example.demo.controllers;

import com.example.demo.models.DTOs.PersonDTO;
import com.example.demo.services.YouthServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/youth")
public class YouthController {
    @Autowired
    private YouthServices youthServices;
    @PostMapping("add_youth")
    public boolean addYouth(@RequestBody PersonDTO personDTO){
        return youthServices.addYouth(personDTO);
    }
}
