package com.example.demo.controllers;

import com.example.demo.models.DTOs.YouthDTO;
import com.example.demo.models.DTOs.YouthFiltersDTO;
import com.example.demo.models.DTOs.YouthIntermediateDTO;
import com.example.demo.services.YouthServices;
import io.swagger.v3.oas.annotations.Operation;
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

    @PostMapping("add")
    @Operation(description = "to add new youth to the database")
    @ApiResponse(responseCode = "200",useReturnTypeSchema = true,description = "the youth added suceessfully")
    public ResponseEntity<String> addYouth(@Valid @RequestBody YouthDTO youthDTO) {
        youthServices.addYouth(youthDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("youth added!");
    }

    @PostMapping("get_all")
    public ResponseEntity<Page<YouthIntermediateDTO>> getAll(@Valid @RequestBody(required = false)
                                                             YouthFiltersDTO youthFiltersDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(youthServices.getAll(youthFiltersDTO));
    }

    @GetMapping("get")
    public ResponseEntity<YouthDTO> getYouth(@RequestParam Integer youthId) {
        return ResponseEntity.status(HttpStatus.OK).body(youthServices.getYouthDtoById(youthId));
    }

    @PatchMapping("edit")
    public ResponseEntity<String> editYouth(@RequestBody YouthDTO youthDTO) {
        youthServices.editYouth(youthDTO);
        return ResponseEntity.status(HttpStatus.OK).body("youth edited successfully!!");
    }
}