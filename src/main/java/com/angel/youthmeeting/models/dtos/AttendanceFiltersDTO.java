package com.angel.youthmeeting.models.dtos;

import com.angel.youthmeeting.models.enums.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceFiltersDTO extends PaginationDTO{

    @JsonProperty("youthNamePart")
    private String youthNamePart;

    @JsonProperty("familyId")
    private Long familyId;

    @JsonProperty("gender")
    private Gender gender;

    @JsonProperty("meetingYear")
    private Integer meetingYear;

    @JsonProperty("meetingMonth")
    @Range(min = 1, max = 12,message = "validation.error.month.range")
    private Integer meetingMonth;

    @JsonProperty("meetingDay")
    @Range(min = 1, max = 31,message = "validation.error.day.range")
    private Integer meetingDay;

    private Integer raffleAllowTime;
}
