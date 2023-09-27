package com.example.demo.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServantDTO extends BasicEntityDTO{

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("familyId")
    private Long familyId;
}
