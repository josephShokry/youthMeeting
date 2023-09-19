package com.example.demo.services;

import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.models.entities.Area;
import com.example.demo.models.DTOs.AreaDTO;
import com.example.demo.models.DTOs.LightDTO;
import com.example.demo.models.mappers.AreaMapper;
import com.example.demo.repositories.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AreaService implements IAreaService {//todo: add interfaces for all the srevices
    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    private AreaMapper areaMapper;

    public Long addArea(AreaDTO areaDTO) {
        Area area = new Area();
        areaRepository.save(areaMapper.getAreaFromDto(areaDTO, area));
        return area.getId();
    }
//    todo: search for resource bundles
    public Area findById(Long areaId) {
        Optional.ofNullable(areaId).orElseThrow(() -> new DataNotFoundException("the Area id is null"));
        return areaRepository.findById(areaId).orElseThrow(
                 () -> new DataNotFoundException("the required area is not present"));
    }

    public Iterable<LightDTO> findAll() {
        return areaMapper.areasToLightDtos(areaRepository.findAll());
    }
}
