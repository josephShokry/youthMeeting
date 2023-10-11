package com.angel.youthmeeting.controllers;

import com.angel.youthmeeting.util.security.EndPoints;
import com.angel.youthmeeting.models.dtos.FamilyDTO;
import com.angel.youthmeeting.models.dtos.LightDTO;
import com.angel.youthmeeting.services.implementations.FamilyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
