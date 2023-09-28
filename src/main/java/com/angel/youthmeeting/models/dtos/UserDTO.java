package com.angel.youthmeeting.models.dtos;

import com.angel.youthmeeting.models.enums.Roles;
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
