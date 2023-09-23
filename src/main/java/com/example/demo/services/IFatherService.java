package com.example.demo.services;

import com.example.demo.models.dtos.FatherDTO;
import com.example.demo.models.dtos.LightDTO;
import com.example.demo.models.entities.Father;

public interface IFatherService {
    public Long addFather(FatherDTO fatherDTO);

    public Iterable<LightDTO> getAll();

    public Father getById(Long fatherId);

}
