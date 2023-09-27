package com.example.demo.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class MeetingDTO extends BasicEntityDTO{
    private String name;
    private String topic;
    private String date;
    private Long instructorId;
    private Set<Long> attendanceIds;
}
