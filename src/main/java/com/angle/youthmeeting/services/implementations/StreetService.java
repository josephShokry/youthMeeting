package com.angle.youthmeeting.services.implementations;

import com.angle.youthmeeting.exceptions.exceptions.DataNotFoundException;
import com.angle.youthmeeting.models.dtos.LightDTO;
import com.angle.youthmeeting.models.dtos.StreetDTO;
import com.angle.youthmeeting.models.entities.Street;
import com.angle.youthmeeting.models.mappers.LightDTOMapper;
import com.angle.youthmeeting.models.mappers.StreetMapper;
import com.angle.youthmeeting.repositories.StreetRepository;
import com.angle.youthmeeting.services.IStreetService;
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
