package com.angel.youthmeeting.controllers;

import com.angel.youthmeeting.models.dtos.YouthDTO;
import com.angel.youthmeeting.models.dtos.YouthFiltersDTO;
import com.angel.youthmeeting.models.dtos.YouthMidLevelDTO;
import com.angel.youthmeeting.services.implementations.YouthService;
import com.angel.youthmeeting.util.security.EndPoints;
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
@RequestMapping(EndPoints.YOUTH)
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
    @PostMapping(EndPoints.ADD_YOUTH)
    @PreAuthorize("hasRole('ROLE_SERVANT_HEAD') or @roleChecker.sameFamily(authentication, #youthDTO) ")
    public ResponseEntity<Boolean> addYouth(@Valid @RequestBody YouthDTO youthDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(youthService.addYouth(youthDTO));
    }

    @Operation(
            summary = "Use this api to list all youths depending on some filters criteria",
            description = "To get all the youths stored in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The youths retrieved successfully")
    })
    @PostMapping(EndPoints.GET_ALL)
    @PreAuthorize("hasRole('ROLE_SERVANT_HEAD')")
    public ResponseEntity<Page<YouthMidLevelDTO>> getAll(@Valid @RequestBody YouthFiltersDTO youthFiltersDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(youthService.findAll(youthFiltersDTO));
    }

    @Operation(
            summary = "Use this api to get all the details of a specific youth",
            description = "Get details of a specific youth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The youth retrieved successfully")
    })
    @GetMapping(EndPoints.GET_YOUTH)
    @PreAuthorize("hasRole('ROLE_SERVANT_HEAD') or @roleChecker.sameFamily(authentication, #youthId)")
    public ResponseEntity<YouthDTO> getYouth(@RequestParam Long youthId) {
        return ResponseEntity.status(HttpStatus.OK).body(youthService.findYouthDtoById(youthId));
    }

    @Operation(
            summary = "Use this api to edit any detail of a specific youth",
            description = "Edit the details of a specific youth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The youth edited successfully")
    })
    @PatchMapping(EndPoints.EDIT_YOUTH)
    @PreAuthorize("hasRole('ROLE_SERVANT_HEAD') or @roleChecker.sameFamily(authentication, #youthDTO)")
    public ResponseEntity<Boolean> editYouth(@Valid @RequestBody YouthDTO youthDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(youthService.editYouth(youthDTO));
    }
}