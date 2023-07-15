package com.example.demo.services;

import com.example.demo.models.DTOs.YouthIntermediateDTO;
import com.example.demo.models.mappers.LightYouthMapper;
import com.example.demo.models.mappers.YouthIntermediateMapper;
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
    private final YouthIntermediateMapper youthIntermediateMapper = Mappers.getMapper(YouthIntermediateMapper.class);



    public boolean addYouth(PersonDTO personDTO) {
        Youth youth = new Youth();
        youthMapper.youthDtoToYouth(personDTO, youth, familyServices, areaServices, streetServices);
        //TODO: do the following 2 lines as the family using the mapper

        youthRepository.save(youth);
        return true;
    }
    public Youth getYouthById(int youthId){
        return youthRepository.findById(youthId).get();
    }
    public PersonDTO getYouthDtoById(int youthId){
        return youthMapper.youthToYouthDto(getYouthById(youthId), new PersonDTO());
    }

    public String getArea (int youthId) {return getYouthById(youthId).getArea().getAreaName();}
    public String getStreet(int youthId) {
        return getYouthById(youthId).getStreet().getStreetName();
    }


    public List<YouthIntermediateDTO> getAll() {
        Iterable<Youth> youths = youthRepository.findAll();
        return (List<YouthIntermediateDTO>) youthIntermediateMapper.youthsToYouthIntermediateDtos(youths);
    }

    public boolean editYouth(int youthId, PersonDTO personDTO) {
        Youth youth = getYouthById(youthId);
        youthMapper.youthDtoToYouth(personDTO, youth, familyServices, areaServices, streetServices);
        youthRepository.save(youth);
        return true;
    }
}
