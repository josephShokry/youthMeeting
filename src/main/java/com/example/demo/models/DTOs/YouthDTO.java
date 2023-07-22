package com.example.demo.models.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
public class YouthDTO {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("firstName")
    @NotBlank(message = "please specify the first name")
    public String firstName;
    @JsonProperty("lastName")
    @NotBlank(message = "please specify the last name")
    public String lastName;
    @JsonProperty("phoneNumber")
    @Pattern(regexp = "01[0-2,5]{1}[0-9]{8}", message = "please specify a valid phone number")
    public String phoneNumber;
    @JsonProperty("dayOfBirth")
    @Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2]\\d|3[0-1])$",
            message = "please specify a proper format of the day of brith in the format of '2002-02-22'")
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
    @Min(1)
    public Integer meetingLevel;
    @JsonProperty("notes")
    public String notes;
    @JsonProperty("familyId")
    @Min(1)
    public Integer familyId;
    @JsonProperty("streetId")
    @Min(1)
    public Integer streetId;
    @JsonProperty("areaId")
    @Min(1)
    public Integer areaId;
    @JsonProperty("buildingNumber")
    @Min(1)
    public Integer buildingNumber;
}
