package com.example.demo.models.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class YouthDTO {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("firstName")
    @NotBlank(message = "please specify the first name")
    private String firstName;
    @JsonProperty("lastName")
    @NotBlank(message = "please specify the last name")
    private String lastName;
    @JsonProperty("phoneNumber")
    @Size(min = 11,max = 11)
    @NotBlank(message = "please specify the phone number")
    @Pattern(regexp = "01[0-2,5]\\d{8}", message = "please specify a valid phone number")
    private String phoneNumber;
    @JsonProperty("dayOfBirth")
    @Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2]\\d|3[0-1])$",
            message = "please specify a proper format of the day of brith in the format of '2002-02-22'")
    private String dayOfBirth;
    @JsonProperty("university")
    private String university;
    @JsonProperty("college")
    private String college;
    @JsonProperty("collegeLevel")
    private String collegeLevel;
    @JsonProperty("gradLevel")
    private Integer gradLevel;
    @JsonProperty("meetingLevel")
    @Min(1)
    private Integer meetingLevel;
    @JsonProperty("notes")
    private String notes;
    @JsonProperty("familyId")
    @Min(1)
    private Long familyId;
    @JsonProperty("streetId")
    @Min(1)
    private Long streetId;
    @JsonProperty("buildingNumber")
    @Min(1)
    private Integer buildingNumber;
}
