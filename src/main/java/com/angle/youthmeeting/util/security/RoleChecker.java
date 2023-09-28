package com.angle.youthmeeting.util.security;

import com.angle.youthmeeting.models.dtos.YouthDTO;
import com.angle.youthmeeting.models.entities.Servant;
import com.angle.youthmeeting.models.entities.User;
import com.angle.youthmeeting.models.entities.Youth;
import com.angle.youthmeeting.services.implementations.ServantService;
import com.angle.youthmeeting.services.implementations.YouthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RoleChecker {

    @Autowired
    private YouthService youthService;

    @Autowired
    private ServantService servantService;

    public Boolean sameFamily(Authentication authentication, Long youthId){
        User principal = (User) authentication.getPrincipal();
        Servant servant = (Servant)principal.getPerson();
        Youth youth = youthService.findYouthById(youthId);
        return Objects.equals(youth.getFamily().getId(), servant.getFamily().getId());
    }

    public Boolean sameFamily(Authentication authentication, YouthDTO youthDTO){
        //should I find a better way of the casting
        User principal = (User) authentication.getPrincipal();
        Long userFamilyId = ((Servant)principal.getPerson()).getFamily().getId();
        return Objects.equals(youthDTO.getFamilyId(), userFamilyId);
    }
}
