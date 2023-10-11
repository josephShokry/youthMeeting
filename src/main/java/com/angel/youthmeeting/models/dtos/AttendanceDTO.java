package com.angel.youthmeeting.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AttendanceDTO extends BasicEntityDTO{

    private Long youthId;

    private Long meetingId;

    private LocalTime arrivingTime;
}
