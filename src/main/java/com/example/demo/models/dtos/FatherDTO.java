package com.example.demo.models.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class FatherDTO extends BasicEntityDTO{

    private String firstName;

    private String lastName;

    private String church;
}
