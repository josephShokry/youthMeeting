package com.angel.youthmeeting.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class FatherDTO extends BasicEntityDTO{

    private String firstName;

    private String lastName;

    private String church;
}
