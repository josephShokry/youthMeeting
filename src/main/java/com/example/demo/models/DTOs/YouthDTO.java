package com.example.demo.models.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
public class YouthDTO {

    @JsonProperty("id")
    public Integer id;
    @NotEmpty(message = "you have to specify the youth first name")
    @JsonProperty("firstName")
    public String firstName;
    @NotEmpty(message = "you have to specify the youth last name")
    @JsonProperty("lastName")
    public String lastName;
    @NotEmpty(message = "you have to specify the youth phone number")
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
