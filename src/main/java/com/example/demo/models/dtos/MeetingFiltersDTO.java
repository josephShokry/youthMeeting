package com.example.demo.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Range;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
public class MeetingFiltersDTO extends PaginationDTO{

    @JsonProperty("topic")
    private String topic;

    @JsonProperty("year")
    private Integer year;

    @JsonProperty("month")
    @Range(min = 1, max = 12,message = "validation.error.month.range")
    private Integer month;

    @JsonProperty("day")
    @Range(min = 1, max = 31,message = "validation.error.day.range")
    private Integer day;

    @JsonProperty("instructorId")
    @Min(value = 1,message = "validation.error.instructorId.min")
    private Integer instructorId;
}
