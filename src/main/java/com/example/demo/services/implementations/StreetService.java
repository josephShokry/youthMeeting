package com.example.demo.services.implementations;

import com.example.demo.exceptions.exceptions.DataNotFoundException;
import com.example.demo.models.dtos.LightDTO;
import com.example.demo.models.dtos.StreetDTO;
import com.example.demo.models.entities.Street;
import com.example.demo.models.mappers.LightDTOMapper;
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

    @Autowired
    private LightDTOMapper lightDTOMapper;

    public Long addStreet(StreetDTO streetDTO) {
        Street street = new Street();
        streetRepository.save(streetMapper.mapStreetDTO(streetDTO, street, areaService));
        return street.getId();
    }

    public Street findById(Long streetId) {
        streetId = Optional.ofNullable(streetId).orElseThrow(() -> new DataNotFoundException("validation.error.streetId"));
        return streetRepository.findById(streetId).orElseThrow(
                () ->new DataNotFoundException("validation.error.street"));
    }

    public Iterable<LightDTO> findAll() {
        return lightDTOMapper.mapListOfStreets(streetRepository.findAll());

    }
}
