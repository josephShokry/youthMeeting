package com.example.demo.controllers;

import com.example.demo.models.DTOs.AreaDTO;
import com.example.demo.models.DTOs.LightDTO;
import com.example.demo.services.AreaServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/area")
public class AreaController {
    @Autowired
    private AreaServices areaServices;
    @Operation(
            summary = "Use this api to add new area to the database",
            description = "To add new area to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The area is added successfully")
    })
    @PostMapping("add_area")
    public ResponseEntity<Integer> addArea(@RequestBody AreaDTO areaDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(areaServices.addArea(areaDTO));
    }
    @Operation(
            summary = "Use this api to list all areas in the dropdown list",
            description = "To list all the areas in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The areas retrieved successfully")
    })
    @GetMapping("get_all")
    public ResponseEntity<Iterable<LightDTO>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(areaServices.getAll());
    }
}
