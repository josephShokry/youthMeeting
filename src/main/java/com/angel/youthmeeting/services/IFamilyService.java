package com.angel.youthmeeting.services;

import com.angel.youthmeeting.models.dtos.FamilyDTO;
import com.angel.youthmeeting.models.dtos.LightDTO;
import com.angel.youthmeeting.models.entities.Family;

public interface IFamilyService {

    Long addFamily(FamilyDTO familyDTO);

    Family findFamilyById(Long familyId);

    Iterable<LightDTO> findAll();
}