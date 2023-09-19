package com.example.demo.controllers;

import com.example.demo.models.DTOs.YouthDTO;
import com.example.demo.models.DTOs.YouthFiltersDTO;
import com.example.demo.models.DTOs.YouthMidLevelDTO;
import com.example.demo.services.implementations.YouthService;
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
    private YouthService youthService;

    @PostMapping("add")
    public ResponseEntity<Boolean> addYouth(@Valid @RequestBody YouthDTO youthDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(youthService.addYouth(youthDTO));
    }

    @PostMapping("get_all")
    public ResponseEntity<Page<YouthMidLevelDTO>> getAll(@Valid @RequestBody(required = false)
                                                             YouthFiltersDTO youthFiltersDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(youthService.findAll(youthFiltersDTO));
    }

    @GetMapping("get")
    public ResponseEntity<YouthDTO> getYouth(@RequestParam Long youthId) {
        return ResponseEntity.status(HttpStatus.OK).body(youthService.findYouthDtoById(youthId));
    }

    @PatchMapping("edit")
    public ResponseEntity<Boolean> editYouth(@RequestBody YouthDTO youthDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(youthService.editYouth(youthDTO));
    }
}