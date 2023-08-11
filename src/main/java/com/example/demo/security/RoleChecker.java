package com.example.demo.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class RoleChecker {
    public Boolean check(Authentication authentication, String userName){
        if(authentication.getName().equals(userName))return true;
        return false;
    }
}
