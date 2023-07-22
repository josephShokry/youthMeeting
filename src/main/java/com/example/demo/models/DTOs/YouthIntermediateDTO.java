package com.example.demo.models.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class YouthIntermediateDTO {
    @JsonProperty("id")
    public Integer id;
    @JsonProperty("firstName")
    public String firstName;
    @JsonProperty("lastName")
    public String lastName;
    @JsonProperty("meetingLevel")
    public Integer meetingLevel;
    @JsonProperty("familyId")
    public Integer familyId;
}
