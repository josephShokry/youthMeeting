package com.angle.youthmeeting.services;

import com.angle.youthmeeting.models.dtos.ServantDTO;
import com.angle.youthmeeting.models.entities.Servant;

public interface IServantService {

    Long addServant(ServantDTO servantDTO);

    Servant getServantById(Long servantId);
}
