package com.angle.youthmeeting.services.implementations;

import com.angle.youthmeeting.exceptions.exceptions.DataNotFoundException;
import com.angle.youthmeeting.models.entities.Area;
import com.angle.youthmeeting.models.dtos.LightDTO;
import com.angle.youthmeeting.models.mappers.AreaMapper;
import com.angle.youthmeeting.models.mappers.LightDTOMapper;
import com.angle.youthmeeting.repositories.AreaRepository;
import com.angle.youthmeeting.services.IAreaService;
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
