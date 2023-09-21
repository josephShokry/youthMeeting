package com.example.demo.services.implementations;

import com.example.demo.exceptions.exceptions.DataNotFoundException;
import com.example.demo.models.dtos.FamilyDTO;
import com.example.demo.models.dtos.LightDTO;
import com.example.demo.models.entities.Family;
import com.example.demo.models.mappers.FamilyMapper;
import com.example.demo.models.mappers.LightDTOMapper;
import com.example.demo.repositories.FamilyRepository;
import com.example.demo.services.IFamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FamilyService implements IFamilyService {
    @Autowired
    private FamilyRepository familyRepository;
    @Autowired
    private FamilyMapper familyMapper;
    @Autowired
    private LightDTOMapper lightDTOMapper;

    public Long addFamily(FamilyDTO familyDTO) {
        Family family = new Family();
        familyRepository.save(familyMapper.mapFamilyDTO(familyDTO, family));
        return family.getId();
    }

    public Family findFamilyById(Long familyId) {
        familyId = Optional.ofNullable(familyId).orElseThrow(() -> new DataNotFoundException("validation.error.familyId"));
        return familyRepository.findById(familyId).orElseThrow(
                ()-> new DataNotFoundException("validation.error.family"));
    }

    public Iterable<LightDTO> findAll() {
        return lightDTOMapper.mapListOfFamilies(familyRepository.findAll());
    }
}
