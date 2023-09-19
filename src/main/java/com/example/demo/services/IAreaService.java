package com.example.demo.services;

import com.example.demo.models.DTOs.AreaDTO;
import com.example.demo.models.DTOs.LightDTO;
import com.example.demo.models.entities.Area;

public interface IAreaService {
    Long addArea(AreaDTO areaDTO);
    Area findById(Long areaId);
    Iterable<LightDTO> findAll();
}