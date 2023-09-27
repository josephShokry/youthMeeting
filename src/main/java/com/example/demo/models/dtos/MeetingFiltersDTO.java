package com.example.demo.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

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
    private Integer month;

    @JsonProperty("day")
    private Integer day;

    @JsonProperty("instructorId")
    private Integer instructorId;
}
