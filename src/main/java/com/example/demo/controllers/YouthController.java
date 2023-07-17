package com.example.demo.controllers;

import com.example.demo.models.DTOs.YouthDTO;
import com.example.demo.models.DTOs.YouthIntermediateDTO;
import com.example.demo.services.YouthServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/youth")
public class YouthController {
    @Autowired
    private YouthServices youthServices;
    @PostMapping("add")
    public ResponseEntity<String> addYouth(@RequestBody YouthDTO youthDTO){
        youthServices.addYouth(youthDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("youth added!");
    }

    @GetMapping("get_all")
    public ResponseEntity<Page<YouthIntermediateDTO>> getAll(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size,
                                                             @RequestParam(required = false) Integer familyId,
                                                             @RequestParam(required = false) Integer streetId){
        return ResponseEntity.status(HttpStatus.OK).body(youthServices.getAll(page, size, familyId, streetId));
    }
    @GetMapping("get")
    public ResponseEntity<YouthDTO> getYouth(@RequestParam int youthId){
        return ResponseEntity.status(HttpStatus.OK).body(youthServices.getYouthDtoById(youthId));

    }
    @PatchMapping("edit")
    public ResponseEntity<String> editYouth(@RequestParam int youthId, @RequestBody YouthDTO youthDTO){
        youthServices.editYouth(youthId, youthDTO);
        return ResponseEntity.status(HttpStatus.OK).body("youth edited successfully!!");
    }

    @GetMapping("get_area")
    public String getAreaName(@RequestParam int youthId){
        return youthServices.getArea(youthId);
    }
    @GetMapping("get_street")
    public String getStreetName(@RequestParam int youthId){
        return youthServices.getStreet(youthId);
    }
}
