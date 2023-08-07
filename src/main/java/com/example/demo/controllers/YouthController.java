package com.example.demo.controllers;

import com.example.demo.models.DTOs.YouthDTO;
import com.example.demo.models.DTOs.YouthFiltersDTO;
import com.example.demo.models.DTOs.YouthIntermediateDTO;
import com.example.demo.services.YouthServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/youth")
@CrossOrigin
public class YouthController {
    @Autowired
    private YouthServices youthServices;

    @Operation(
            summary = "Use this api to add new youth",
            description = "To add new youth to the database")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "the youth added suceessfully")
    })
    @PostMapping("add")
    public ResponseEntity<String> addYouth(@Valid @RequestBody YouthDTO youthDTO) {
        youthServices.addYouth(youthDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("youth added!");
    }

    @Operation(
            summary = "Use this api to list all youths depending on some filters criteria",
            description = "To get all the youths stored in the database")
    @PostMapping("get_all")
    public ResponseEntity<Page<YouthIntermediateDTO>> getAll(@Valid @RequestBody(required = false)
                                                             YouthFiltersDTO youthFiltersDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(youthServices.getAll(youthFiltersDTO));
    }

    @Operation(
            summary = "Use this api to get all the details of a specific youth",
            description = "Get details of a specific youth")
    @GetMapping("get")
    public ResponseEntity<YouthDTO> getYouth(@RequestParam Integer youthId) {
        return ResponseEntity.status(HttpStatus.OK).body(youthServices.getYouthDtoById(youthId));
    }

    @Operation(
            summary = "Use this api to edit any detail of a specific youth",
            description = "Edit the details of a specific youth")
    @PatchMapping("edit")
    public ResponseEntity<String> editYouth(@RequestBody YouthDTO youthDTO) {
        youthServices.editYouth(youthDTO);
        return ResponseEntity.status(HttpStatus.OK).body("youth edited successfully!!");
    }
}