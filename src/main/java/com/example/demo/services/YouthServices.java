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

    public boolean addYouth(PersonDTO personDTO) {
        EntityMappers youthMapper = Mappers.getMapper(EntityMappers.class);
        Youth youth = new Youth();
        youthRepository.save(youthMapper.getYouthFromDto(personDTO, youth));
        System.out.println("**************************");
        System.out.println(youth.getFirstName());
//        Youth youth = Youth.builder()
//                .college(personDTO.college)
//                .collegeLevel(personDTO.collegeLevel)
//                .gradLevel(personDTO.gradLevel)
//                .build();
//        youth.setFirstName(personDTO.firstName);
//        youth.setLastName(personDTO.lastName);
//        youth.setPhoneNumber(personDTO.phoneNumber);
//        youthRepository.save(youth);
        return true;
    }
}
