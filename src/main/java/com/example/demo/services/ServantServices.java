package com.example.demo.services;

import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.models.DTOs.ServantDTO;
import com.example.demo.models.entities.Servant;
import com.example.demo.models.mappers.ServantMapper;
import com.example.demo.repositories.ServantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServantServices {
    @Autowired
    private ServantRepository servantRepository;
    @Autowired
    private FamilyServices familyServices;
    @Autowired
    private ServantMapper servantMapper;

    public Integer addServant(ServantDTO servantDTO) {
        Servant servant = new Servant();
        servantRepository.save(servantMapper.servantDtoToServant(servantDTO, new Servant(), familyServices));
        return servant.getId();
    }

    public Servant getServantById(Integer id) {
        return servantRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("the required servant is not present"));
    }
}
