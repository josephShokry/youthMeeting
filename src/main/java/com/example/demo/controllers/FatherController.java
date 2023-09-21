package com.example.demo.controllers;

import com.example.demo.models.DTOs.FatherDTO;
import com.example.demo.models.DTOs.LightDTO;
import com.example.demo.services.implementations.FatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/father")
public class FatherController {
    @Autowired
    private FatherService fatherService;

    @PostMapping("add_father")
    @PreAuthorize("hasRole('ROLE_Servant_Head')")
    public ResponseEntity<Integer> addFather(@RequestBody FatherDTO fatherDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(fatherService.addFather(fatherDTO));
    }
    @GetMapping("get_all")
    public ResponseEntity<Iterable<LightDTO>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(fatherService.getAll());
    }
}
