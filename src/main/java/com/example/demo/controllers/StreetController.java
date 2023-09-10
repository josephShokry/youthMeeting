package com.example.demo.controllers;

import com.example.demo.models.DTOs.LightDTO;
import com.example.demo.models.DTOs.StreetDTO;
import com.example.demo.services.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/street")
public class StreetController {
    @Autowired
    private StreetService streetService;
    @PostMapping("add_street")
    public ResponseEntity<Long> addStreet(@RequestBody StreetDTO streetDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(streetService.addStreet(streetDTO));
    }
    @GetMapping("get_all")
    public ResponseEntity<Iterable<LightDTO>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(streetService.findAll());
    }
}
