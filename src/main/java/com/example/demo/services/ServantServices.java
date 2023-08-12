package com.example.demo.services;

import com.example.demo.models.DTOs.ServantDTO;
import com.example.demo.models.Servant;
import com.example.demo.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServantServices {
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private FamilyServices familyServices;

    public Integer addServant(ServantDTO servantDTO) {
        Servant servant = new Servant();
        servant.setFirstName(servantDTO.firstName);
        servant.setFamily(familyServices.getFamilyById(servantDTO.familyId));
        serviceRepository.save(servant);
        return servant.getId();
    }

    public Servant getServantById(Integer id) {
        return serviceRepository.findById(id).get();
    }
}
