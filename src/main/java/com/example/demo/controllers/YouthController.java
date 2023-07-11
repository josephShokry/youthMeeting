package com.example.demo.controllers;

import com.example.demo.models.DTOs.LightDTO;
import com.example.demo.models.DTOs.PersonDTO;
import com.example.demo.services.YouthServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/youth")
public class YouthController {
    @Autowired
    private YouthServices youthServices;
    @PostMapping("add")
    public boolean addYouth(@RequestBody PersonDTO personDTO){
        return youthServices.addYouth(personDTO);
    }

    @GetMapping("get_all")
    public List<LightDTO> getAll(){
        return youthServices.getAll();
    }
    @GetMapping("get_family_name")
    public String getFamilyName(@RequestParam int youthId){
        return youthServices.getFamilyName(youthId);
    }
}
