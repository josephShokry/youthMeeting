package com.example.demo.services;

import com.example.demo.models.dtos.ServantDTO;
import com.example.demo.models.entities.Servant;

public interface IServantService {
    public Long addServant(ServantDTO servantDTO);

    public Servant getServantById(Long id);
}
