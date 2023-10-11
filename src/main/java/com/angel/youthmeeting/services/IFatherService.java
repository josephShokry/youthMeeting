package com.angel.youthmeeting.services;

import com.angel.youthmeeting.models.dtos.FatherDTO;
import com.angel.youthmeeting.models.dtos.LightDTO;
import com.angel.youthmeeting.models.entities.Father;

public interface IFatherService {

    Long addFather(FatherDTO fatherDTO);

    Iterable<LightDTO> getAll();

    Father findById(Long fatherId);

}
