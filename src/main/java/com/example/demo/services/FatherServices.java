package com.example.demo.services;

import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.models.DTOs.FatherDTO;
import com.example.demo.models.DTOs.LightDTO;
import com.example.demo.models.entities.Father;
import com.example.demo.models.mappers.FatherMapper;
import com.example.demo.repositories.FatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FatherServices {
    @Autowired
    private FatherRepository fatherRepository;
    @Autowired
    private FatherMapper fatherMapper;
    public Integer addFather(FatherDTO fatherDTO) {
        Father father = new Father();
        fatherRepository.save(fatherMapper.fatherDtoToFather(fatherDTO, father));
        return father.getId();
    }

    public Iterable<LightDTO> getAll() {
        return fatherMapper.fathersToLightDtos(fatherRepository.findAll());
    }

    public Father getById(Integer fatherId) {
        Optional.ofNullable(fatherId).orElseThrow(() -> new DataNotFoundException("the father id is null"));
        return fatherRepository.findById(fatherId).orElseThrow(
                ()-> new DataNotFoundException("the required father is not present"));
    }
}