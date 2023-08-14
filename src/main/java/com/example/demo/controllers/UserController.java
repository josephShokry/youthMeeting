package com.example.demo.controllers;

import com.example.demo.models.DTOs.UserDTO;
import com.example.demo.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user/")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("sing_up")
    public String regenerate(@RequestBody UserDTO userDTO){
        userService.addUser(userDTO);
        return "true";
    }
}
