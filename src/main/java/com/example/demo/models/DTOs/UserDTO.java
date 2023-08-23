package com.example.demo.models.DTOs;

import com.example.demo.security.Roles;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    public String username;
    public String password;
//    public boolean enabled;
//    public List<Roles> roles;
    public Roles roles;
    public Integer personId;
}
