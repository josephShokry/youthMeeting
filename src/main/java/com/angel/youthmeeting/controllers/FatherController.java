package com.angel.youthmeeting.controllers;

import com.angel.youthmeeting.models.dtos.FatherDTO;
import com.angel.youthmeeting.models.dtos.LightDTO;
import com.angel.youthmeeting.services.implementations.FatherService;
import com.angel.youthmeeting.util.security.EndPoints;
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
@RequestMapping(EndPoints.FATHER)
public class FatherController {

    @Autowired
    private FatherService fatherService;

    @Operation(
            summary = "Use this api to add new father",
            description = "To add new father to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "the father added successfully")
    })
    @PostMapping(EndPoints.ADD_FATHER)
    @PreAuthorize("hasRole('ROLE_SERVANT_HEAD')")
    public ResponseEntity<Long> addFather(@RequestBody FatherDTO fatherDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(fatherService.addFather(fatherDTO));
    }

    @Operation(
            summary = "Use this api to list all fathers in the dropdown list",
            description = "To list all the fathers in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The fathers retrieved successfully")
    })
    @GetMapping(EndPoints.GET_ALL)
    public ResponseEntity<Iterable<LightDTO>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(fatherService.getAll());
    }
}
