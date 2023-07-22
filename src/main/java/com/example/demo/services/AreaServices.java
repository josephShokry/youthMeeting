package com.example.demo.services;

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

    public boolean addArea(AreaDTO areaDTO) {
        Area area = new Area();
        areaRepository.save(areaMapper.getAreaFromDto(areaDTO, area));
        return true;
    }

    public Area getById(Integer areaId) {
        return areaRepository.findById(areaId).get();
    }

    public Iterable<LightDTO> getAll() {
        return areaMapper.areasToLightDtos(areaRepository.findAll());
    }
}
