package com.angle.youthmeeting.services;

import com.angle.youthmeeting.models.dtos.LightDTO;
import com.angle.youthmeeting.models.entities.Area;

public interface IAreaService {

    Long addArea(LightDTO areaLightDTO);

    Area findById(Long areaId);

    Iterable<LightDTO> findAll();
}