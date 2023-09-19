package com.example.demo.services;

import com.example.demo.models.DTOs.LightDTO;
import com.example.demo.models.DTOs.StreetDTO;
import com.example.demo.models.entities.Street;

public interface IStreetService {
    Long addStreet(StreetDTO streetDTO);
    Street findById(Long streetId);
    Iterable<LightDTO> findAll();
}
