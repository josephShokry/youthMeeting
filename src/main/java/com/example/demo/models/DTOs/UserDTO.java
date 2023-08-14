package com.example.demo.models.DTOs;

import com.example.demo.security.Roles;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class UserDTO {
    public String username;
    public String password;
    public boolean enabled;
    public List<GrantedAuthority> authorities;
    public Roles roles;
    public Integer personId;
}
