package com.example.demo.services;

import com.example.demo.models.DTOs.EntityMappers;
import com.example.demo.models.DTOs.PersonDTO;
import com.example.demo.models.Youth;
import com.example.demo.repositories.YouthRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YouthServices {
    @Autowired
    private YouthRepository youthRepository;
    @Autowired
    private FamilyServices familyServices;

    public boolean addYouth(PersonDTO personDTO) {
        EntityMappers youthMapper = Mappers.getMapper(EntityMappers.class);
        Youth youth = new Youth();
        youthMapper.getYouthFromDto(personDTO, youth);
        youth.setFamily(familyServices.getById(personDTO.familyId));
        youthRepository.save(youth);
        return true;
    }
    public Youth getYouthById(int youthId){
        return youthRepository.findById(youthId).get();
    }

    public String getFamilyName(int youthId) {
        return getYouthById(youthId).getFamily().getFamilyName();
    }
}
