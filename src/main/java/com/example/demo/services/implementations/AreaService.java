package com.example.demo.services.implementations;

import com.example.demo.exceptions.exceptions.DataNotFoundException;
import com.example.demo.models.dtos.LightDTO;
import com.example.demo.models.entities.Area;
import com.example.demo.models.mappers.AreaMapper;
import com.example.demo.models.mappers.LightDTOMapper;
import com.example.demo.repositories.AreaRepository;
import com.example.demo.services.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AreaService implements IAreaService {

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private AreaMapper areaMapper;

    @Autowired
    private LightDTOMapper lightDTOMapper;

    public Long addArea(LightDTO areaLightDTO) {
        Area area = new Area();
        areaRepository.save(areaMapper.mapLightDTO(areaLightDTO, area));
        return area.getId();
    }

    public Area findById(Long areaId) {
        areaId = Optional.ofNullable(areaId).orElseThrow(() -> new DataNotFoundException("validation.error.areaId"));
        return areaRepository.findById(areaId).orElseThrow(
                 () -> new DataNotFoundException("validation.error.area"));
    }

    public Iterable<LightDTO> findAll() {
        return lightDTOMapper.mapListOfAreas(areaRepository.findAll());
    }
}
