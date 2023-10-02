package com.angel.youthmeeting.services;

import com.angel.youthmeeting.models.dtos.LightDTO;
import com.angel.youthmeeting.models.entities.Area;

public interface IAreaService {

    Long addArea(LightDTO areaLightDTO);

    Area findById(Long areaId);

    Iterable<LightDTO> findAll();
}