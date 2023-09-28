package com.angle.youthmeeting.services.implementations;

import com.angle.youthmeeting.exceptions.exceptions.DataNotFoundException;
import com.angle.youthmeeting.models.dtos.ServantDTO;
import com.angle.youthmeeting.models.entities.Servant;
import com.angle.youthmeeting.models.mappers.ServantMapper;
import com.angle.youthmeeting.repositories.ServantRepository;
import com.angle.youthmeeting.services.IServantService;
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
