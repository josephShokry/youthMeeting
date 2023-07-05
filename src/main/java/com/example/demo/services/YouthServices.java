package com.example.demo.services;

import com.example.demo.models.DTOs.PersonDTO;
import com.example.demo.models.Youth;
import com.example.demo.repositories.YouthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YouthServices {
    @Autowired
    private YouthRepository youthRepository;

    public boolean addYouth(PersonDTO personDTO) {
        Youth youth = Youth.builder()
                .college(personDTO.college)
                .collegeLevel(personDTO.collegeLevel)
                .gradLevel(personDTO.gradLevel)
                .build();
        youth.setFirstName(personDTO.firstName);
        youth.setLastName(personDTO.lastName);
        youth.setPhoneNumber(personDTO.phoneNumber);
        youthRepository.save(youth);
        return true;
    }
}
