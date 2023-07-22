package com.example.demo.controllers;

import com.example.demo.models.DTOs.YouthDTO;
import com.example.demo.models.DTOs.YouthFiltersDTO;
import com.example.demo.models.DTOs.YouthIntermediateDTO;
import com.example.demo.services.YouthServices;
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
    public ResponseEntity<String> addYouth(@Valid @RequestBody YouthDTO youthDTO){
        youthServices.addYouth(youthDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("youth added!");
    }

    @GetMapping("get_all")
    public ResponseEntity<Page<YouthIntermediateDTO>> getAll(@RequestParam(defaultValue = "0") Integer page,
                                                             @RequestParam(defaultValue = "10") Integer size,
                                                             @RequestBody(required = false)YouthFiltersDTO youthFiltersDTO){
        return ResponseEntity.status(HttpStatus.OK).body(youthServices.getAll(page, size, youthFiltersDTO));
    }
    @GetMapping("get")
    public ResponseEntity<YouthDTO> getYouth(@RequestParam Integer youthId){
        return ResponseEntity.status(HttpStatus.OK).body(youthServices.getYouthDtoById(youthId));

    }
    @PatchMapping("edit")
    public ResponseEntity<String> editYouth(@RequestParam Integer youthId, @RequestBody YouthDTO youthDTO){
        youthServices.editYouth(youthId, youthDTO);
        return ResponseEntity.status(HttpStatus.OK).body("youth edited successfully!!");
    }

    @GetMapping("get_area")
    public String getAreaName(@RequestParam Integer youthId){
        return youthServices.getArea(youthId);
    }
    @GetMapping("get_street")
    public String getStreetName(@RequestParam Integer youthId){
        return youthServices.getStreet(youthId);
    }
}
