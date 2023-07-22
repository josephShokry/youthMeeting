package com.example.demo.models.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
public class YouthDTO {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("firstName")
    public String firstName;
    @JsonProperty("lastName")
    public String lastName;
    @JsonProperty("phoneNumber")
    public String phoneNumber;
    public String dayOfBirth;
    @JsonProperty("university")
    public String university;
    @JsonProperty("college")
    public String college;
    @JsonProperty("collegeLevel")
    public Integer collegeLevel;
    @JsonProperty("gradLevel")
    public Integer gradLevel;
    @JsonProperty("meetingLevel")
    public Integer meetingLevel;
    @JsonProperty("notes")
    public String notes;
    @JsonProperty("familyId")
    public Integer familyId;
    @JsonProperty("streetId")
    public Integer streetId;
    @JsonProperty("areaId")
    public Integer areaId;
    @JsonProperty("buildingNumber")
    public Integer buildingNumber;
}
