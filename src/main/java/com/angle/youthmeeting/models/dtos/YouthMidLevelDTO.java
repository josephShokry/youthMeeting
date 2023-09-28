package com.angle.youthmeeting.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class YouthMidLevelDTO extends BasicEntityDTO{

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("meetingLevel")
    private Integer meetingLevel;

    @JsonProperty("familyId")
    private Long familyId;
}
