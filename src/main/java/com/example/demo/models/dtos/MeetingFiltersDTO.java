package com.example.demo.models.dtos;

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
    private String topic;
    private Integer year;
    private Integer month;
    private Integer day;
    private Integer instructorId;
}
