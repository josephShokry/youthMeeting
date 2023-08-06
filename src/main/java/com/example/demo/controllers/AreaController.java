package com.example.demo.controllers;

import com.example.demo.models.DTOs.AreaDTO;
import com.example.demo.models.DTOs.LightDTO;
import com.example.demo.services.AreaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/area")
public class AreaController {
    @Autowired
    private AreaServices areaServices;
    @PostMapping("add_area")
    public ResponseEntity<Integer> addArea(@RequestBody AreaDTO areaDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(areaServices.addArea(areaDTO));
    }
    @GetMapping("get_all")
    public ResponseEntity<Iterable<LightDTO>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(areaServices.getAll());
    }
}
