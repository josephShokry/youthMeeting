package com.example.demo.controllers;

import com.example.demo.models.DTOs.ServantDTO;
import com.example.demo.services.ServantServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/servant")
public class ServantController {
    @Autowired
    private ServantServices servantServices;

    @PostMapping("add_servant")
    public ResponseEntity<Integer> addServant(@RequestBody ServantDTO servantDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(servantServices.addServant(servantDTO));
    }
    @GetMapping()
    public String s(){
        return "hiiii";
    }

//    @GetMapping("get_all")
//    public ResponseEntity<Iterable<LightDTO>> getAll(){
//        return ResponseEntity.status(HttpStatus.OK).body(areaServices.getAll());
//    }
}
