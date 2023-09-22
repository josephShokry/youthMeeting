package com.example.demo.services;

import com.example.demo.models.dtos.LightDTO;
import com.example.demo.models.entities.Area;

public interface IAreaService {
    Long addArea(LightDTO areaLightDTO);
    Area findById(Long areaId);
    Iterable<LightDTO> findAll();
}