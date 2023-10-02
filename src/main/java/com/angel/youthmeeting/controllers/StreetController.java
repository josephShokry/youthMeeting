package com.angel.youthmeeting.controllers;

import com.angel.youthmeeting.models.dtos.StreetDTO;
import com.angel.youthmeeting.util.security.EndPoints;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import com.angel.youthmeeting.models.dtos.LightDTO;
import com.angel.youthmeeting.services.implementations.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(EndPoints.STREET)
public class StreetController {

    @Autowired
    private StreetService streetService;

    @Operation(
            summary = "Use this api to add new street to the database",
            description = "To add new street to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The street is added successfully")
    })
    @PostMapping(EndPoints.ADD_STREET)
    public ResponseEntity<Long> addStreet(@RequestBody StreetDTO streetDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(streetService.addStreet(streetDTO));
    }

    @Operation(
            summary = "Use this api to list all street in the dropdown list",
            description = "To list all the street in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The street retrieved successfully")
    })
    @GetMapping(EndPoints.GET_ALL)
    public ResponseEntity<Iterable<LightDTO>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(streetService.findAll());
    }
}
