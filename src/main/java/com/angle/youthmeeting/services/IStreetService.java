package com.angle.youthmeeting.services;

import com.angle.youthmeeting.models.dtos.LightDTO;
import com.angle.youthmeeting.models.dtos.StreetDTO;
import com.angle.youthmeeting.models.entities.Street;

public interface IStreetService {

    Long addStreet(StreetDTO streetDTO);

    Street findById(Long streetId);

    Iterable<LightDTO> findAll();
}
