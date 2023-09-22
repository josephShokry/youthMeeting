package com.example.demo.services.implementations;

import com.example.demo.exceptions.exceptions.DataNotFoundException;
import com.example.demo.models.DTOs.ServantDTO;
import com.example.demo.models.entities.Servant;
import com.example.demo.models.mappers.ServantMapper;
import com.example.demo.repositories.ServantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServantService {
    @Autowired
    private ServantRepository servantRepository;
    @Autowired
    private FamilyService familyService;
    @Autowired
    private ServantMapper servantMapper;

    public Long addServant(ServantDTO servantDTO) {
        Servant servant = new Servant();
        servantRepository.save(servantMapper.servantDtoToServant(servantDTO, new Servant(), familyService));
        return servant.getId();
    }

    public Servant getServantById(Long id) {
        return servantRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("the required servant is not present"));
    }
}
