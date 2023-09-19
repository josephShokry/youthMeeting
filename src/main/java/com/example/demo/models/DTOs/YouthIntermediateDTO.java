package com.example.demo.models.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class YouthIntermediateDTO { //TODO change the name
    @JsonProperty("id")
    public Long id;
    @JsonProperty("firstName")
    public String firstName;
    @JsonProperty("lastName")
    public String lastName;
    @JsonProperty("meetingLevel")
    public Integer meetingLevel;
    @JsonProperty("familyId")
    public Long familyId;
}
