package com.example.demo.controllers;

import com.example.demo.util.security.EndPoints;
import com.example.demo.models.dtos.FamilyDTO;
import com.example.demo.models.dtos.LightDTO;
import com.example.demo.services.implementations.FamilyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(EndPoints.FAMILY)
public class FamilyController {

    @Autowired
    private FamilyService familyService;

    @Operation(
            summary = "Use this api to add new family to the database",
            description = "To get add new family to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The family is added successfully")
    })
    @PostMapping(EndPoints.ADD_FAMILY)
    @PreAuthorize("hasRole('ROLE_SERVANT_HEAD')")
    public ResponseEntity<Long> addFamily(@RequestBody FamilyDTO familyDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(familyService.addFamily(familyDTO));
    }

    @Operation(
            summary = "Use this api to list all families in the dropdown list",
            description = "To list all the families in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The families retrieved successfully")
    })
    @GetMapping(EndPoints.GET_ALL)
    public ResponseEntity<Iterable<LightDTO>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(familyService.findAll());
    }
}
