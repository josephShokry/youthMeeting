package com.example.demo.controllers;

import com.example.demo.models.DTOs.FamilyDTO;
import com.example.demo.models.DTOs.LightDTO;
import com.example.demo.services.FamilyServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/family")
public class FamilyController {
    @Autowired
    private FamilyServices familyServices;
    @Operation(
            summary = "Use this api to add new family to the database",
            description = "To get add new family to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The family is added successfully")
    })
    @PostMapping("add_family")
    public ResponseEntity<Integer> addFamily(@RequestBody FamilyDTO familyDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(familyServices.addFamily(familyDTO));
    }
    @Operation(
            summary = "Use this api to list all families in the dropdown list",
            description = "To list all the families in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The families retrieved successfully")
    })
    @GetMapping("get_all")
    public ResponseEntity<Iterable<LightDTO>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(familyServices.getAll());
    }

}
