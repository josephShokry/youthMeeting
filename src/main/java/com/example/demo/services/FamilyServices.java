package com.example.demo.services;

import com.example.demo.models.DTOs.FamilyDTO;
import com.example.demo.models.Family;
import com.example.demo.models.mappers.FamilyMapper;
import com.example.demo.repositories.FamilyRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FamilyServices {
    @Autowired
    private FamilyRepository familyRepository;


    public boolean addFamily(FamilyDTO familyDTO) {
        FamilyMapper familyMapper = Mappers.getMapper(FamilyMapper.class);
        Family family = new Family();
        familyRepository.save(familyMapper.familyDtoToFamily(familyDTO, family));
        return true;
    }

    public Family getFamilyById(int familyId) {
        return familyRepository.findById(familyId).get();
    }
}
