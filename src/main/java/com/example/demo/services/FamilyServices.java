package com.example.demo.services;

import com.example.demo.models.DTOs.EntityMappers;
import com.example.demo.models.DTOs.FamilyDTO;
import com.example.demo.models.Family;
import com.example.demo.models.Youth;
import com.example.demo.repositories.FamilyRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FamilyServices {
    @Autowired
    private FamilyRepository familyRepository;


    public boolean addFamily(FamilyDTO familyDTO) {
        EntityMappers familyMapper = Mappers.getMapper(EntityMappers.class);
        Family family = new Family();
        familyRepository.save(familyMapper.getFamilyFromDto(familyDTO, family));
        return true;
    }

    public Family getById(int familyId) {
        return familyRepository.findById(familyId).get();
    }
}
