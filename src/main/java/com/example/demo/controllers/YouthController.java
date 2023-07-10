package com.example.demo.controllers;

import com.example.demo.models.DTOs.PersonDTO;
import com.example.demo.services.YouthServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/youth")
public class YouthController {
    @Autowired
    private YouthServices youthServices;
    @PostMapping("add_youth")
    public boolean addYouth(@RequestBody PersonDTO personDTO){
        return youthServices.addYouth(personDTO);
    }
    @GetMapping("get_family_name")
    public String getFamilyName(@RequestParam int youthId){
        System.out.println("#############################333reached");
        return youthServices.getFamilyName(youthId);
    }
}
