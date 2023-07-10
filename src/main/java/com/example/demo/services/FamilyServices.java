package com.example.demo.services;

import com.example.demo.models.DTOs.FamilyDTO;
import com.example.demo.models.Family;
import com.example.demo.repositories.FamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FamilyServices {
    @Autowired
    private FamilyRepository familyRepository;


    public boolean addFamily(FamilyDTO familyDTO) {
//        Family family= Family.builder()
//                .familyLevel(familyDTO.familyLevel)
//                .familyName(familyDTO.familyName)
//                .joiningYear(familyDTO.joiningYear)
//                .build();
//        familyRepository.save(family);
        return true;
    }
}
