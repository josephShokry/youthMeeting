package com.example.demo.services;

import com.example.demo.models.Address;
import com.example.demo.models.DTOs.AddressDTO;
import com.example.demo.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServices {
    @Autowired
    private AddressRepository addressRepository;
    public boolean addAddress(AddressDTO addressDTO) {
        Address address= Address.builder()
                .area(addressDTO.area)
                .buildingNumber(addressDTO.buildingNumber)
                //.streetName(addressDTO.streetName)
                .build();
        addressRepository.save(address);
        return true;
    }
    }
