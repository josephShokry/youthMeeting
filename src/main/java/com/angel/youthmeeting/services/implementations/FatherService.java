package com.angel.youthmeeting.services.implementations;

import com.angel.youthmeeting.exceptions.exceptions.DataNotFoundException;
import com.angel.youthmeeting.models.dtos.FatherDTO;
import com.angel.youthmeeting.models.dtos.LightDTO;
import com.angel.youthmeeting.models.entities.Father;
import com.angel.youthmeeting.models.mappers.FatherMapper;
import com.angel.youthmeeting.models.mappers.LightDTOMapper;
import com.angel.youthmeeting.repositories.FatherRepository;
import com.angel.youthmeeting.services.IFatherService;
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

    public Father findById(Long fatherId) {
        fatherId = Optional.ofNullable(fatherId).orElseThrow(() -> new DataNotFoundException("validation.error.fatherId"));
        return fatherRepository.findById(fatherId).orElseThrow(
                ()-> new DataNotFoundException("validation.error.father"));
    }
}