package com.example.demo.services;

import com.example.demo.models.dtos.ServantDTO;
import com.example.demo.models.entities.Servant;

public interface IServantService {

    Long addServant(ServantDTO servantDTO);

    Servant getServantById(Long id);
}
