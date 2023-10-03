package com.angel.youthmeeting.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ServantDTO extends BasicEntityDTO{

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("familyId")
    private Long familyId;
}
