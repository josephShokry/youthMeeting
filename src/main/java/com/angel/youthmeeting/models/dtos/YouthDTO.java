package com.angel.youthmeeting.models.dtos;

import com.angel.youthmeeting.models.enums.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class YouthDTO extends BasicEntityDTO{

    @JsonProperty("firstName")
    @NotBlank(message = "validation.error.firstName")
    private String firstName;

    @JsonProperty("lastName")
    @NotBlank(message = "validation.error.lastName")
    private String lastName;

    @JsonProperty("phoneNumber")
    @Size(min = 11,max = 11, message = "validation.error.phoneNumber.size")
    @NotBlank(message = "validation.error.phoneNumber")
    @Pattern(regexp = "01[0-2,5]\\d{8}", message = "validation.error.phoneNumber.pattern")
    private String phoneNumber;

    @JsonProperty("dayOfBirth")
    @Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2]\\d|3[0-1])$",
            message = "validation.error.dayOfBirth.pattern")
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
    @Min(value = 1, message = "validation.error.meetingLevel.min")
    private Integer meetingLevel;

    @JsonProperty("notes")
    private String notes;

    @JsonProperty("familyId")
    @Min(value = 1, message = "validation.error.familyId.min")
    @NotNull(message = "validation.error.familyId")
    private Long familyId;
    @Min(value = 1, message = "validation.error.fatherId.min")
    private Long fatherId;

    @JsonProperty("streetId")
    @Min(value = 1, message = "validation.error.streetId.min")
    private Long streetId;

    @JsonProperty("buildingNumber")
    @Min(value = 1, message = "validation.error.buildingNumber.min")
    private Integer buildingNumber;

    @JsonProperty("gender")
    @NotNull(message = "validation.error.gender")
    private Gender gender;
}
