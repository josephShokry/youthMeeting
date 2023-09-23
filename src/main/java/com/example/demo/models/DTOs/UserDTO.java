package com.example.demo.models.dtos;

import com.example.demo.models.enums.Roles;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String username;
    private String password;
    private Roles role;
    private Long personId;
}
