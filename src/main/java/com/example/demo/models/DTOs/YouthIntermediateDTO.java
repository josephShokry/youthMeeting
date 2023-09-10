package com.example.demo.models.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class YouthIntermediateDTO {
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
