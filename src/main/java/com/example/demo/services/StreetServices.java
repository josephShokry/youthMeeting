package com.example.demo.services;

import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.models.DTOs.LightDTO;
import com.example.demo.models.DTOs.StreetDTO;
import com.example.demo.models.Street;
import com.example.demo.models.mappers.StreetMapper;
import com.example.demo.repositories.StreetRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class StreetServices {
    @Autowired
    private StreetRepository streetRepository;
    final private StreetMapper streetMapper = Mappers.getMapper(StreetMapper.class);

    public Integer addStreet(StreetDTO streetDTO) {
        Street street = new Street();
        streetRepository.save(streetMapper.getStreetFromDto(streetDTO, street));
        return street.getId();
    }

    public Street getById(Integer streetId) {
        Optional.ofNullable(streetId).orElseThrow(() -> new DataNotFoundException("the street id is null"));
        Street street = streetRepository.findById(streetId).orElseThrow(
                () ->new DataNotFoundException("the required street not present"));
        return street;
    }

    public Iterable<LightDTO> getAll() {
        return streetMapper.streetsToLightDtos(streetRepository.findAll());

    }
}
