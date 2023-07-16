package com.example.demo.models.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonDTO {
    @JsonProperty("id")
    public int id;
    @JsonProperty("firstName")
    public String firstName;
    @JsonProperty("lastName")
    public String lastName;
    @JsonProperty("phoneNumber")
    public String phoneNumber;
//    public Timestamp dayOfBirth;
    @JsonProperty("university")
    public String university;
    @JsonProperty("college")
    public String college;
    @JsonProperty("collegeLevel")
    public int collegeLevel;
    @JsonProperty("gradLevel")
    public int gradLevel;
    @JsonProperty("meetingLevel")
    public int meetingLevel;
    //    public Father father;
    @JsonProperty("notes")
    public String notes;
    @JsonProperty("familyId")
    public int familyId;
//    public FamilyLightDTO family;
    @JsonProperty("streetId")
    public int streetId;
    @JsonProperty("areaId")
    public int areaId;
    @JsonProperty("buildingNumber")
    public int buildingNumber;

}
