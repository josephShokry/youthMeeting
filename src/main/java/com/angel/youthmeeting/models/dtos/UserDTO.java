package com.angel.youthmeeting.models.dtos;

import com.angel.youthmeeting.models.enums.Roles;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String username;

    private String password;

    private Roles role;

    @NotNull
    @Min(1)
    private Long personId;
}
