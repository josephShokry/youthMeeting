package com.example.demo.services;

import com.example.demo.models.DTOs.FamilyDTO;
import com.example.demo.models.DTOs.LightDTO;
import com.example.demo.models.entities.Family;

public interface IFamilyService {
    Long addFamily(FamilyDTO familyDTO);
    Family findFamilyById(Long familyId);
    Iterable<LightDTO> findAll();
}