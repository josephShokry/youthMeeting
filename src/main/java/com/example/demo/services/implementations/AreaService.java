package com.example.demo.services.implementations;

import com.example.demo.exceptions.exceptions.DataNotFoundException;
import com.example.demo.models.entities.Area;
import com.example.demo.models.DTOs.LightDTO;
import com.example.demo.models.mappers.AreaMapper;
import com.example.demo.models.mappers.LightDTOMapper;
import com.example.demo.repositories.AreaRepository;
import com.example.demo.services.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AreaService implements IAreaService {//todo: add interfaces for all the srevices
    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    private AreaMapper areaMapper;
    @Autowired
    private LightDTOMapper lightDTOMapper;

    public Long addArea(LightDTO areaLightDTO) {
        Area area = new Area();
        areaRepository.save(areaMapper.getAreaFromDto(areaLightDTO, area));
        return area.getId();
    }
//    todo: search for resource bundles
    public Area findById(Long areaId) {
        Optional.ofNullable(areaId).orElseThrow(() -> new DataNotFoundException("the Area id is null"));
        return areaRepository.findById(areaId).orElseThrow(
                 () -> new DataNotFoundException("the required area is not present"));
    }

    public Iterable<LightDTO> findAll() {
        return lightDTOMapper.areasToLightDtos(areaRepository.findAll());
    }
}
