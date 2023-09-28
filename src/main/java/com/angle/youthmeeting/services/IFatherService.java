package com.angle.youthmeeting.services;

import com.angle.youthmeeting.models.dtos.FatherDTO;
import com.angle.youthmeeting.models.dtos.LightDTO;
import com.angle.youthmeeting.models.entities.Father;

public interface IFatherService {

    Long addFather(FatherDTO fatherDTO);

    Iterable<LightDTO> getAll();

    Father getById(Long fatherId);

}
