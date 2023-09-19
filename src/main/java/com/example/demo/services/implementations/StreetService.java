package com.example.demo.services.implementations;

import com.example.demo.exceptions.exceptions.DataNotFoundException;
import com.example.demo.models.DTOs.LightDTO;
import com.example.demo.models.DTOs.StreetDTO;
import com.example.demo.models.entities.Street;
import com.example.demo.models.mappers.StreetMapper;
import com.example.demo.repositories.StreetRepository;
import com.example.demo.services.IStreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class StreetService implements IStreetService {
    @Autowired
    private StreetRepository streetRepository;
    @Autowired
    private AreaService areaService;
    @Autowired
    private StreetMapper streetMapper;

    public Long addStreet(StreetDTO streetDTO) {
        Street street = new Street();
        streetRepository.save(streetMapper.streetDtoToStreet(streetDTO, street, areaService));
        return street.getId();
    }

    public Street findById(Long streetId) {
        Optional.ofNullable(streetId).orElseThrow(() -> new DataNotFoundException("the street id is null"));
        return streetRepository.findById(streetId).orElseThrow(
                () ->new DataNotFoundException("the required street not present"));
    }

    public Iterable<LightDTO> findAll() {
        return streetMapper.streetsToLightDtos(streetRepository.findAll());

    }
}
