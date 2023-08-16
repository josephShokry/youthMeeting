package com.example.demo.controllers;

import com.example.demo.models.DTOs.ServantDTO;
import com.example.demo.services.ServantServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/servant")
public class ServantController {
    @Autowired
    private ServantServices servantServices;

    @PostMapping("add_servant")
    @PreAuthorize("hasRole('ROLE_Servant_Head')")
    public ResponseEntity<Integer> addServant(@RequestBody ServantDTO servantDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(servantServices.addServant(servantDTO));
    }

//    @GetMapping("get_all")
//    public ResponseEntity<Iterable<LightDTO>> getAll(){
//        return ResponseEntity.status(HttpStatus.OK).body(areaServices.getAll());
//    }
}
