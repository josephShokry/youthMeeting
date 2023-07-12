package com.example.demo.services;

import com.example.demo.models.DTOs.EntityMappers;
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
    private final EntityMappers youthMapper = Mappers.getMapper(EntityMappers.class);

    public boolean addYouth(PersonDTO personDTO) {
        Youth youth = new Youth();
        youthMapper.youthDtoToYouth(personDTO, youth);
        youth.setFamily(familyServices.getFamilyById(personDTO.familyId));
        youthRepository.save(youth);
        return true;
    }
    private Youth getYouthEntityById(int youthId){
        return youthRepository.findById(youthId).get();
    }
    public PersonDTO getYouthById(int youthId){
        Youth youth  = youthRepository.findById(youthId).get();
        PersonDTO dto = youthMapper.youthsToYouthLightDto(youthRepository.findById(youthId).get(),new PersonDTO());
        return dto;
    }
//
//
//    public String getFamilyName(int youthId) {
//        return getYouthById(youthId).getFamily().getFamilyName();
//    }

    public List<YouthLightDTO> getAll() {
        Iterable<Youth> youths = youthRepository.findAll();
        return (List<YouthLightDTO>) youthMapper.youthsToYouthLightDto(youths);
    }

    public boolean editYouth(int youthId, PersonDTO personDTO) {
        Youth youth = getYouthEntityById(youthId);
        youthMapper.youthDtoToYouth(personDTO, youth);
        youthRepository.save(youth);
        // TODO: what if the change was in the family or any foreign key i think this will not work
        return true;
    }
}
