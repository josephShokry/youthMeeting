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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/youth")
@CrossOrigin
public class YouthController {
    @Autowired
    private YouthServices youthServices;

    @PostMapping("add")
    @PreAuthorize("hasRole('ROLE_Servant_Head') or @roleChecker.sameFamily(authentication, #youthDTO) ")
    public ResponseEntity<String> addYouth(@Valid @RequestBody YouthDTO youthDTO) {
        youthServices.addYouth(youthDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("youth added!");
    }

    @PostMapping("get_all")
    @PreAuthorize("hasRole('ROLE_Servant_Head')")
    public ResponseEntity<Page<YouthIntermediateDTO>> getAll(@Valid @RequestBody(required = false)
                                                             YouthFiltersDTO youthFiltersDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(youthServices.getAll(youthFiltersDTO));
    }

    @GetMapping("get")
    @PreAuthorize("hasRole('ROLE_Servant_Head') or @roleChecker.sameFamily(authentication, #youthId)")
    public ResponseEntity<YouthDTO> getYouth(@RequestParam Integer youthId) {
        return ResponseEntity.status(HttpStatus.OK).body(youthServices.getYouthDtoById(youthId));
    }

    @PatchMapping("edit")
    @PreAuthorize("hasRole('ROLE_Servant_Head') or @roleChecker.sameFamily(authentication, #youthDTO)")
    public ResponseEntity<String> editYouth(@Valid @RequestBody YouthDTO youthDTO) {
        youthServices.editYouth(youthDTO);
        return ResponseEntity.status(HttpStatus.OK).body("youth edited successfully!!");
    }
}