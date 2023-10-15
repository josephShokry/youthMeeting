package com.angel.youthmeeting.models.mappers;

import com.angel.youthmeeting.models.dtos.AttendanceDTO;
import com.angel.youthmeeting.models.entities.Attendance;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AttendanceMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "youthId", source = "youth.id")
    @Mapping(target = "meetingId", source = "meeting.id")
//    @Mapping(target = "arrivingTime", expression = "java(attendance.getTime() != null ?" +
//            " java.time.LocalTime.parse(attendance.getTime()) : null)")
    AttendanceDTO mapAttendance(Attendance attendance, @MappingTarget AttendanceDTO attendanceDTO);
}
