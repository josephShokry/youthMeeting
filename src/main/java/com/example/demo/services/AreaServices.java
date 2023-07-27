package com.example.demo.services;

import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.models.Area;
import com.example.demo.models.DTOs.AreaDTO;
import com.example.demo.models.DTOs.LightDTO;
import com.example.demo.models.mappers.AreaMapper;
import com.example.demo.repositories.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AreaServices {
    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    private AreaMapper areaMapper;

    public Integer addArea(AreaDTO areaDTO) {
        Area area = new Area();
        areaRepository.save(areaMapper.getAreaFromDto(areaDTO, area));
        return area.getId();
    }

    public Area getById(Integer areaId) {
        Optional.ofNullable(areaId).orElseThrow(() -> new DataNotFoundException("the Area id is null"));
        Area area = areaRepository.findById(areaId).orElseThrow(
                 () -> new DataNotFoundException("the required area is not present"));
        return area;
    }

    public Iterable<LightDTO> getAll() {
        return areaMapper.areasToLightDtos(areaRepository.findAll());
    }
}
