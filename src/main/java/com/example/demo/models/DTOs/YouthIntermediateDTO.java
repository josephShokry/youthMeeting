package com.example.demo.models.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class YouthIntermediateDTO {
    @JsonProperty("id")
    public int id;
    @JsonProperty("firstName")
    public String firstName;
    @JsonProperty("lastName")
    public String lastName;
    @JsonProperty("meetingLevel")
    public int meetingLevel;
    @JsonProperty("familyId")
    public int familyId;
}
