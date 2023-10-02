package com.angel.youthmeeting.services.implementations;

import com.angel.youthmeeting.models.dtos.ServantDTO;
import com.angel.youthmeeting.models.mappers.ServantMapper;
import com.angel.youthmeeting.repositories.ServantRepository;
import com.angel.youthmeeting.exceptions.exceptions.DataNotFoundException;
import com.angel.youthmeeting.models.entities.Servant;
import com.angel.youthmeeting.services.IServantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServantService implements IServantService {

    @Autowired
    private ServantRepository servantRepository;

    @Autowired
    private FamilyService familyService;

    @Autowired
    private ServantMapper servantMapper;

    public Long addServant(ServantDTO servantDTO) {
        Servant servant = new Servant();
        servantRepository.save(servantMapper.mapToServant(servantDTO, new Servant(), familyService));
        return servant.getId();
    }

    public Servant getServantById(Long servantId) {
        servantId = Optional.ofNullable(servantId).orElseThrow(() -> new DataNotFoundException("validation.error.servantId"));
        return servantRepository.findById(servantId).orElseThrow(
                () ->new DataNotFoundException("validation.error.servant"));
    }
}
