package com.example.demo.models.DTOs;

import com.example.demo.security.Roles;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    public String username;
    public String password;
    public boolean enabled;
    public List<GrantedAuthority> authorities;
    public Roles roles;
    public Integer personId;
}
