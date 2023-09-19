package com.example.demo.services;

import com.example.demo.models.DTOs.LightDTO;
import com.example.demo.models.entities.Area;

public interface IAreaService {
    Long addArea(LightDTO areaLightDTO);
    Area findById(Long areaId);
    Iterable<LightDTO> findAll();
}