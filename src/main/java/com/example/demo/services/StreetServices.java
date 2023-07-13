package com.example.demo.services;

import com.example.demo.models.mappers.EntityMappers;
import com.example.demo.models.DTOs.StreetDTO;
import com.example.demo.models.Street;
import com.example.demo.repositories.StreetRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class StreetServices {
    @Autowired
    private StreetRepository streetRepository;
    public boolean addStreet(StreetDTO streetDTO) {
        EntityMappers streetMapper = Mappers.getMapper(EntityMappers.class);
        Street street = new Street();
        streetRepository.save(streetMapper.getStreetFromDto(streetDTO, street));
        return true;
    }

    public Street getById(int streetId) {
        return streetRepository.findById(streetId).get();
    }
}
