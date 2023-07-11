package com.example.demo.services;

import com.example.demo.models.Area;
import com.example.demo.models.DTOs.AreaDTO;
import com.example.demo.models.DTOs.EntityMappers;
import com.example.demo.models.DTOs.FamilyDTO;
import com.example.demo.models.Family;
import com.example.demo.repositories.AreaRepository;
import com.example.demo.repositories.FamilyRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AreaServices {
    @Autowired
    private AreaRepository areaRepository;


    public boolean addArea(AreaDTO areaDTO) {
        EntityMappers areaMapper = Mappers.getMapper(EntityMappers.class);
        Area area = new Area();
        areaRepository.save(areaMapper.getAreaFromDto(areaDTO, area));
        return true;
    }

    public Area getById(int areaId) {
        return areaRepository.findById(areaId).get();
    }
}
