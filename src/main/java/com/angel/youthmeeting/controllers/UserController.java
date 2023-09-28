package com.angel.youthmeeting.controllers;

import com.angel.youthmeeting.models.dtos.UserDTO;
import com.angel.youthmeeting.util.security.EndPoints;
import com.angel.youthmeeting.services.implementations.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(EndPoints.USER)
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(
            summary = "Use this api to register new use to the system",
            description = "To get add new user to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The user is registered successfully")
    })
    @PostMapping(EndPoints.USER_REGISTER)
    public ResponseEntity<Boolean> register(@RequestBody UserDTO userDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(userDTO));
    }
}
