package com.example.demo.services.implementations;

import com.example.demo.exceptions.exceptions.DataNotFoundException;
import com.example.demo.models.dtos.FatherDTO;
import com.example.demo.models.dtos.LightDTO;
import com.example.demo.models.entities.Father;
import com.example.demo.models.mappers.FatherMapper;
import com.example.demo.models.mappers.LightDTOMapper;
import com.example.demo.repositories.FatherRepository;
import com.example.demo.services.IFatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FatherService implements IFatherService {

    @Autowired
    private FatherRepository fatherRepository;

    @Autowired
    private FatherMapper fatherMapper;

    @Autowired
    private LightDTOMapper lightDTOMapper;

    public Long addFather(FatherDTO fatherDTO) {
        Father father = new Father();
        fatherRepository.save(fatherMapper.mapToFather(fatherDTO, father));
        return father.getId();
    }

    public Iterable<LightDTO> getAll() {
        return lightDTOMapper.mapListOfFathers(fatherRepository.findAll());
    }

    public Father getById(Long fatherId) {
        fatherId = Optional.ofNullable(fatherId).orElseThrow(() -> new DataNotFoundException("validation.error.fatherId"));
        return fatherRepository.findById(fatherId).orElseThrow(
                ()-> new DataNotFoundException("validation.error.father"));
    }
}