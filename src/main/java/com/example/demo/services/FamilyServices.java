package com.example.demo.services;

import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.models.DTOs.FamilyDTO;
import com.example.demo.models.DTOs.LightDTO;
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
    final private FamilyMapper familyMapper = Mappers.getMapper(FamilyMapper.class);

    public void addFamily(FamilyDTO familyDTO) {
        familyRepository.save(familyMapper.familyDtoToFamily(familyDTO, new Family()));
    }

    public Family getFamilyById(Integer familyId) {
        Family family = familyRepository.findById(familyId).orElseThrow(
                ()-> new DataNotFoundException("the required family is not present"));
        return family;
    }

    public Iterable<LightDTO> getAll() {
        return familyMapper.familiesToLightDtos(familyRepository.findAll());
    }
}
