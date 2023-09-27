package com.example.demo.services;

import com.example.demo.models.dtos.FatherDTO;
import com.example.demo.models.dtos.LightDTO;
import com.example.demo.models.entities.Father;

public interface IFatherService {

    Long addFather(FatherDTO fatherDTO);

    Iterable<LightDTO> getAll();

    Father getById(Long fatherId);

}
