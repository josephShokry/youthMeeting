package com.angel.youthmeeting.services;

import com.angel.youthmeeting.models.dtos.AttendanceFiltersDTO;
import com.angel.youthmeeting.models.dtos.YouthMidLevelDTO;
import com.angel.youthmeeting.models.entities.Attendance;

import java.util.List;
import java.util.Map;

public interface IAttendanceService {

    Boolean addAttendance(Long meetingId, Long youthId);

    Map<Long, List<Attendance>> getAttendanceReport(AttendanceFiltersDTO attendanceFiltersDTO);

    List<YouthMidLevelDTO> findTodayAttendance(AttendanceFiltersDTO attendanceFiltersDTO);

    List<YouthMidLevelDTO> getRaffle(AttendanceFiltersDTO attendanceFiltersDTO);

    Map<YouthMidLevelDTO, Long> findAttendanceFrequency(AttendanceFiltersDTO attendanceFiltersDTO);

    List<YouthMidLevelDTO> findAttendanceOfAllMeetings(AttendanceFiltersDTO attendanceFiltersDTO);

}
