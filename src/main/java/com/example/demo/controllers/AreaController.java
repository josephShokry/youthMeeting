package com.example.demo.controllers;

import com.example.demo.util.security.EndPoints;
import com.example.demo.models.dtos.LightDTO;
import com.example.demo.services.implementations.AreaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(EndPoints.AREA)
public class AreaController {

    @Autowired
    private AreaService areaService;

    @Operation(
            summary = "Use this api to add new area to the database",
            description = "To add new area to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The area is added successfully")
    })
    @PostMapping(EndPoints.ADD_AREA)
    public ResponseEntity<Long> addArea(@RequestBody LightDTO areaLightDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(areaService.addArea(areaLightDTO));
    }

    @Operation(
            summary = "Use this api to list all areas in the dropdown list",
            description = "To list all the areas in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The areas retrieved successfully")
    })
    @GetMapping(EndPoints.GET_ALL)
    public ResponseEntity<Iterable<LightDTO>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(areaService.findAll());
    }
}
