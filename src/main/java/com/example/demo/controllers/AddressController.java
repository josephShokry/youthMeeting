package com.example.demo.controllers;

import com.example.demo.models.DTOs.AddressDTO;
import com.example.demo.models.DTOs.FamilyDTO;
import com.example.demo.services.AddressServices;
import com.example.demo.services.FamilyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressServices addressServices;
    @PostMapping("add_address")
    public boolean addAddress(@RequestBody AddressDTO addressDTO){
        return addressServices.addAddress(addressDTO);
    }
}
