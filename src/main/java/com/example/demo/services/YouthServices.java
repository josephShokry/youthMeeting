package com.example.demo.services;

import com.example.demo.models.mappers.LightYouthMapper;
import com.example.demo.models.mappers.YouthMapper;
import com.example.demo.models.DTOs.YouthLightDTO;
import com.example.demo.models.DTOs.PersonDTO;
import com.example.demo.models.Youth;
import com.example.demo.repositories.YouthRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    private final YouthMapper youthMapper = Mappers.getMapper(YouthMapper.class);
    private final LightYouthMapper lightYouthMapper = Mappers.getMapper(LightYouthMapper.class);


    public boolean addYouth(PersonDTO personDTO) {
        Youth youth = new Youth();
        youthMapper.youthDtoToYouth(personDTO, youth, familyServices);
        //TODO: do the following 2 lines as the family using the mapper
        youth.setArea(areaServices.getById(personDTO.areaId));
        youth.setStreet(streetServices.getById(personDTO.streetId));
        youthRepository.save(youth);
        return true;
    }
    private Youth getYouthById(int youthId){
        return youthRepository.findById(youthId).get();
    }
//    public PersonDTO getYouthById(int youthId){
//        Youth youth  = youthRepository.findById(youthId).get();
//        PersonDTO dto = youthMapper.youthsToYouthLightDto(youthRepository.findById(youthId).get(),new PersonDTO());
//        return dto;
//    }
//
//
//    public String getFamilyName(int youthId) {
//        return getYouthById(youthId).getFamily().getFamilyName();
//    }
    public String getArea (int youthId) {return getYouthById(youthId).getArea().getAreaName();}
    public String getStreet(int youthId) {
        return getYouthById(youthId).getStreet().getStreetName();
    }


    public List<YouthLightDTO> getAll() {
        Iterable<Youth> youths = youthRepository.findAll();
        return (List<YouthLightDTO>) lightYouthMapper.youthsToYouthLightDto(youths);
    }

    public boolean editYouth(int youthId, PersonDTO personDTO) {
        Youth youth = getYouthById(youthId);
        youthMapper.youthDtoToYouth(personDTO, youth, familyServices);
        youthRepository.save(youth);
        return true;
    }
}
