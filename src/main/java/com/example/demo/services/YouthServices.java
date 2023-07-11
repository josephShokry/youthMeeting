package com.example.demo.services;

import com.example.demo.models.DTOs.EntityMappers;
import com.example.demo.models.DTOs.LightDTO;
import com.example.demo.models.DTOs.PersonDTO;
import com.example.demo.models.Youth;
import com.example.demo.repositories.YouthRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class YouthServices {
    @Autowired
    private YouthRepository youthRepository;
    @Autowired
    private FamilyServices familyServices;
    @Autowired

    private AreaServices areaServices;
    @Autowired

    private StreetServices streetServices;

    private final EntityMappers youthMapper = Mappers.getMapper(EntityMappers.class);

    public boolean addYouth(PersonDTO personDTO) {
        Youth youth = new Youth();
        youthMapper.getYouthFromDto(personDTO, youth);
        youth.setFamily(familyServices.getById(personDTO.familyId));
        youth.setArea(areaServices.getById(personDTO.areaId));
        youth.setStreet(streetServices.getById(personDTO.streetId));
        youthRepository.save(youth);
        return true;
    }
    public Youth getYouthById(int youthId){
        return youthRepository.findById(youthId).get();
    }

    public String getFamilyName(int youthId) {
        return getYouthById(youthId).getFamily().getFamilyName();
    }
    public String getArea (int youthId) {return getYouthById(youthId).getArea().getAreaName();}
    public String getStreet(int youthId) {
        return getYouthById(youthId).getStreet().getStreetName();
    }


    public List<LightDTO> getAll() {
        Iterable<Youth> youths = youthRepository.findAll();
        List<LightDTO> dtos = StreamSupport.stream(youthRepository.findAll().spliterator(), false)
                .map(entity -> youthMapper.getLightDTOFromYouth(entity,new LightDTO()))
                .collect(Collectors.toList());
        return dtos;
    }
}
