package com.angel.youthmeeting.controllers;

import com.angel.youthmeeting.models.dtos.InstructorDTO;
import com.angel.youthmeeting.services.implementations.InstructorService;
import com.angel.youthmeeting.util.security.EndPoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(EndPoints.INSTRUCTOR)
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @PostMapping
    public ResponseEntity<Long> addInstructor(InstructorDTO instructorDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(instructorService.addInstructor(instructorDTO));
    }
}
