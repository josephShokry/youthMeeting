package com.example.demo.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class YouthMidLevelDTO {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("meetingLevel")
    private Integer meetingLevel;
    @JsonProperty("familyId")
    private Long familyId;
}
