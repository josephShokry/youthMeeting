package com.angel.youthmeeting.services;

import com.angel.youthmeeting.models.dtos.StreetDTO;
import com.angel.youthmeeting.models.dtos.LightDTO;
import com.angel.youthmeeting.models.entities.Street;

public interface IStreetService {

    Long addStreet(StreetDTO streetDTO);

    Street findById(Long streetId);

    Iterable<LightDTO> findAll();
}
