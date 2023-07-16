package com.example.demo.controllers;

import com.example.demo.models.DTOs.FamilyDTO;
import com.example.demo.models.DTOs.LightDTO;
import com.example.demo.services.FamilyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/family")
public class FamilyController {
    @Autowired
    private FamilyServices familyServices;
    @PostMapping("add_family")
    public ResponseEntity<String> addFamily(@RequestBody FamilyDTO familyDTO){
        familyServices.addFamily(familyDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("family added!");
    }
    @GetMapping("get_all")
    public ResponseEntity<Iterable<LightDTO>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(familyServices.getAll());
    }

}
