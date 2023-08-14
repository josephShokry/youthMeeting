package com.example.demo.security;

import com.example.demo.models.Servant;
import com.example.demo.models.Youth;
import com.example.demo.services.ServantServices;
import com.example.demo.services.YouthServices;
import jakarta.security.auth.message.callback.PrivateKeyCallback;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RoleChecker {
    //TODO: if an un authenticated user tried to request get youth details these methods will get executed and will throw exceptions
    @Autowired
    private YouthServices youthServices;
    @Autowired
    private ServantServices servantServices;
    public Boolean check(Authentication authentication, String userName){
        return authentication.getName().equals(userName);
    }
    public Boolean sameFamily(Authentication authentication, Integer youthId){
        User principal = (User) authentication.getPrincipal();
        if(principal.getRoles() == Roles.ROLE_Servant_Head) return true;
        Servant servant = servantServices.getServantById(principal.getPerson().getId());
        Youth youth = youthServices.getYouthById(youthId);
        return Objects.equals(youth.getFamily().getId(), servant.getFamily().getId());
    }

    public Boolean sameFamily(Authentication authentication, HttpServletRequestWrapper request){
        System.out.println(request);
//        request
//        request.b
        return true;
    }
}
