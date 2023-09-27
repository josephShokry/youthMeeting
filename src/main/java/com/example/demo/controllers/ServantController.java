package com.example.demo.controllers;

import com.example.demo.util.security.EndPoints;
import com.example.demo.models.dtos.ServantDTO;
import com.example.demo.services.implementations.ServantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(EndPoints.SERVANT)
public class ServantController {

    @Autowired
    private ServantService servantService;

    @Operation(
            summary = "Use this api to add new servant to the database",
            description = "To get add new servant to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The servant is added successfully")
    })
    @PostMapping(EndPoints.ADD_SERVANT)
    @PreAuthorize("hasRole('ROLE_SERVANT_HEAD')")
    public ResponseEntity<Long> addServant(@RequestBody ServantDTO servantDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(servantService.addServant(servantDTO));
    }
}
