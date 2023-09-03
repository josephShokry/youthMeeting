package com.example.demo.services;

import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.models.DTOs.FamilyDTO;
import com.example.demo.models.DTOs.LightDTO;
import com.example.demo.models.entities.Family;
import com.example.demo.models.mappers.FamilyMapper;
import com.example.demo.repositories.FamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FamilyServices {
    @Autowired
    private FamilyRepository familyRepository;
    @Autowired
    private FamilyMapper familyMapper;

    public Integer addFamily(FamilyDTO familyDTO) {
        Family family = new Family();
        familyRepository.save(familyMapper.familyDtoToFamily(familyDTO, family));
        return family.getId();
    }

    public Family getFamilyById(Integer familyId) {
        Optional.ofNullable(familyId).orElseThrow(() -> new DataNotFoundException("the family id is null"));
        return familyRepository.findById(familyId).orElseThrow(
                ()-> new DataNotFoundException("the required family is not present"));
    }

    public Iterable<LightDTO> getAll() {
        return familyMapper.familiesToLightDtos(familyRepository.findAll());
    }
}
