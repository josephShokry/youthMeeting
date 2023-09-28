package com.angle.youthmeeting.services;

import com.angle.youthmeeting.models.dtos.FamilyDTO;
import com.angle.youthmeeting.models.dtos.LightDTO;
import com.angle.youthmeeting.models.entities.Family;

public interface IFamilyService {

    Long addFamily(FamilyDTO familyDTO);

    Family findFamilyById(Long familyId);

    Iterable<LightDTO> findAll();
}