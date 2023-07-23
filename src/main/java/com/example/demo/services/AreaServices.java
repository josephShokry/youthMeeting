package com.example.demo.services;

import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.models.Area;
import com.example.demo.models.DTOs.AreaDTO;
import com.example.demo.models.DTOs.LightDTO;
import com.example.demo.models.mappers.AreaMapper;
import com.example.demo.repositories.AreaRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AreaServices {
    @Autowired
    private AreaRepository areaRepository;
    final private AreaMapper areaMapper = Mappers.getMapper(AreaMapper.class);

    public void addArea(AreaDTO areaDTO) {
        areaRepository.save(areaMapper.getAreaFromDto(areaDTO, new Area()));
    }

    public Area getById(Integer areaId) {
         Area area = areaRepository.findById(areaId).orElseThrow(
                 () -> new DataNotFoundException("the required area is not present"));
        return area;
    }

    public Iterable<LightDTO> getAll() {
        return areaMapper.areasToLightDtos(areaRepository.findAll());
    }
}
