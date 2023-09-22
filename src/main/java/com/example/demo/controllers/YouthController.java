package com.example.demo.controllers;

import com.example.demo.models.dtos.YouthDTO;
import com.example.demo.models.dtos.YouthFiltersDTO;
import com.example.demo.models.dtos.YouthMidLevelDTO;
import com.example.demo.services.implementations.YouthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/youth")
@CrossOrigin
public class YouthController {
    @Autowired
    private YouthService youthService;

    @Operation(
            summary = "Use this api to add new youth",
            description = "To add new youth to the database")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "the youth added successfully")
    })
    @PostMapping
    @PreAuthorize("hasRole('ROLE_Servant_Head') or @roleChecker.sameFamily(authentication, #youthDTO) ")
    public ResponseEntity<Boolean> addYouth(@Valid @RequestBody YouthDTO youthDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(youthService.addYouth(youthDTO));
    }

    @Operation(
            summary = "Use this api to list all youths depending on some filters criteria",
            description = "To get all the youths stored in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The youths retrieved successfully")
    })
    @PostMapping("all")
    @PreAuthorize("hasRole('ROLE_Servant_Head')")
    public ResponseEntity<Page<YouthMidLevelDTO>> getAll(@Valid @RequestBody YouthFiltersDTO youthFiltersDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(youthService.findAll(youthFiltersDTO));
    }

    @Operation(
            summary = "Use this api to get all the details of a specific youth",
            description = "Get details of a specific youth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The youth retrieved successfully")
    })
    @GetMapping
    @PreAuthorize("hasRole('ROLE_Servant_Head') or @roleChecker.sameFamily(authentication, #youthId)")
    public ResponseEntity<YouthDTO> getYouth(@RequestParam Long youthId) {
        return ResponseEntity.status(HttpStatus.OK).body(youthService.findYouthDtoById(youthId));
    }

    @Operation(
            summary = "Use this api to edit any detail of a specific youth",
            description = "Edit the details of a specific youth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The youth edited successfully")
    })
    @PatchMapping
    @PreAuthorize("hasRole('ROLE_Servant_Head') or @roleChecker.sameFamily(authentication, #youthDTO)")
    public ResponseEntity<Boolean> editYouth(@RequestBody YouthDTO youthDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(youthService.editYouth(youthDTO));
    }
}