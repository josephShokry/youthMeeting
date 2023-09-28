package com.angle.youthmeeting.services.implementations;

import com.angle.youthmeeting.exceptions.exceptions.DataNotFoundException;
import com.angle.youthmeeting.models.entities.Family;
import com.angle.youthmeeting.models.dtos.FamilyDTO;
import com.angle.youthmeeting.models.dtos.LightDTO;
import com.angle.youthmeeting.models.mappers.FamilyMapper;
import com.angle.youthmeeting.models.mappers.LightDTOMapper;
import com.angle.youthmeeting.repositories.FamilyRepository;
import com.angle.youthmeeting.services.IFamilyService;
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
