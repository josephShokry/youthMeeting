package com.example.demo.controllers;

import com.example.demo.models.DTOs.LightDTO;
import com.example.demo.models.DTOs.StreetDTO;
import com.example.demo.services.StreetServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/street")
public class StreetController {
    @Autowired
    private StreetServices streetServices;
    @PostMapping("add_street")
    public ResponseEntity<String> addStreet(@RequestBody StreetDTO streetDTO){
        streetServices.addStreet(streetDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("street created!");
    }
    @GetMapping("get_all")
    public ResponseEntity<Iterable<LightDTO>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(streetServices.getAll());
    }
}
