package com.example.demo.controllers;

import com.example.demo.models.dtos.FamilyDTO;
import com.example.demo.models.dtos.LightDTO;
import com.example.demo.services.implementations.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/family")
public class FamilyController {
    @Autowired
    private FamilyService familyService;
    @PostMapping("add_family")
    public ResponseEntity<Long> addFamily(@RequestBody FamilyDTO familyDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(familyService.addFamily(familyDTO));
    }
    @GetMapping("get_all")
    public ResponseEntity<Iterable<LightDTO>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(familyService.findAll());
    }
}
