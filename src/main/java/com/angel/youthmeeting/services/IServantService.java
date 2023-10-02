package com.angel.youthmeeting.services;

import com.angel.youthmeeting.models.dtos.ServantDTO;
import com.angel.youthmeeting.models.entities.Servant;

public interface IServantService {

    Long addServant(ServantDTO servantDTO);

    Servant getServantById(Long servantId);
}
