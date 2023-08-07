package com.example.demo.controllers;

import com.example.demo.models.DTOs.LightDTO;
import com.example.demo.models.DTOs.StreetDTO;
import com.example.demo.services.StreetServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/street")
public class StreetController {
    @Autowired
    private StreetServices streetServices;
    @Operation(
            summary = "Use this api to add new street to the database",
            description = "To add new street to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The street is added successfully")
    })
    @PostMapping("add_street")
    public ResponseEntity<Integer> addStreet(@RequestBody StreetDTO streetDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(streetServices.addStreet(streetDTO));
    }
    @Operation(
            summary = "Use this api to list all street in the dropdown list",
            description = "To list all the street in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The street retrieved successfully")
    })
    @GetMapping("get_all")
    public ResponseEntity<Iterable<LightDTO>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(streetServices.getAll());
    }
}
