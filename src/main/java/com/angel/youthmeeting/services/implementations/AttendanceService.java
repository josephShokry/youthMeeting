package com.angel.youthmeeting.services.implementations;

import com.angel.youthmeeting.exceptions.exceptions.DuplicateAttendanceException;
import com.angel.youthmeeting.models.dtos.AttendanceFiltersDTO;
import com.angel.youthmeeting.models.dtos.YouthMidLevelDTO;
import com.angel.youthmeeting.models.entities.Attendance;
import com.angel.youthmeeting.models.entities.Meeting;
import com.angel.youthmeeting.models.entities.Youth;
import com.angel.youthmeeting.models.mappers.YouthMapper;
import com.angel.youthmeeting.repositories.AttendanceRepository;
import com.angel.youthmeeting.repositories.AttendanceSpecificationsImpl;
import com.angel.youthmeeting.services.IAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AttendanceService implements IAttendanceService {

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private YouthService youthService;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private YouthMapper youthMapper;

    @Override
    public Boolean addAttendance(Long meetingId, Long youthId) {
        Meeting meeting = meetingService.findMeetingById(meetingId);
        Youth youth = youthService.findYouthById(youthId);
        Boolean attendanceExists = attendanceRepository.existsByMeetingAndYouth(meeting, youth);
        if(Boolean.TRUE.equals(attendanceExists)) throw new DuplicateAttendanceException("validation.error.attendance.duplicate");
        Attendance attendance = Attendance.builder().meeting(meeting).youth(youth).time(LocalTime.now()).build();
        attendanceRepository.save(attendance);
        return true;
    }

    @Override
    public Map<Long, List<Attendance>> getAttendanceReport(AttendanceFiltersDTO attendanceFiltersDTO) {
        Specification<Attendance> spec = new AttendanceSpecificationsImpl(attendanceFiltersDTO);
        List<Attendance> attendances = attendanceRepository.findAll(spec);
        return attendances.stream().collect(Collectors.groupingBy(attendance -> attendance.getMeeting().getId()));
    }

    @Override
    public List<YouthMidLevelDTO> findTodayAttendance(AttendanceFiltersDTO attendanceFiltersDTO){
        LocalDate today = LocalDate.now();
        attendanceFiltersDTO.setMeetingDay(today.getDayOfMonth());
        attendanceFiltersDTO.setMeetingMonth(today.getMonthValue());
        attendanceFiltersDTO.setMeetingYear(today.getYear());
        return findAttendanceOfAllMeetings(attendanceFiltersDTO);
    }

    @Override
    public List<YouthMidLevelDTO> getRaffle(AttendanceFiltersDTO attendanceFiltersDTO) {
        List<YouthMidLevelDTO> attendanceList = findTodayAttendance(attendanceFiltersDTO);
        Collections.shuffle(attendanceList);
        return attendanceList;
    }

    @Override
    public Map<YouthMidLevelDTO, Long> findAttendanceFrequency(AttendanceFiltersDTO attendanceFiltersDTO){
        Map<Long, List<Attendance>> meetingAttendanceMap = this.getAttendanceReport(attendanceFiltersDTO);
        Map<YouthMidLevelDTO, Long> out = new HashMap<>();
        meetingAttendanceMap.values().stream()
                .flatMap(List::stream).collect(Collectors.groupingBy(attendance -> attendance.getYouth().getId(), Collectors.counting()))
                .forEach((key, value) -> {
                    YouthMidLevelDTO youthDTO = youthMapper.mapYouth(youthService.findYouthById(key), new YouthMidLevelDTO());
                    out.put(youthDTO, value);
                });
        return out;
    }

    @Override
    public List<YouthMidLevelDTO> findAttendanceOfAllMeetings(AttendanceFiltersDTO attendanceFiltersDTO){
        // make new filters to filter the attendance on the meeting filters only
        // this solution has a problem that if a meeting has no attendance will not be counted as a meeting with these criteria
        AttendanceFiltersDTO attendanceFiltersDTO1 = AttendanceFiltersDTO.builder()
                .meetingDay(attendanceFiltersDTO.getMeetingDay()).meetingMonth(attendanceFiltersDTO.getMeetingMonth())
                .meetingYear(attendanceFiltersDTO.getMeetingYear()).build();
        Specification<Attendance> spec = new AttendanceSpecificationsImpl(attendanceFiltersDTO1);
        long numberOfMeetings = attendanceRepository.findAll(spec).stream().map(attendance -> attendance.getMeeting().getId()).distinct().count();
        return findAttendanceFrequency(attendanceFiltersDTO).entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(numberOfMeetings))
                .map(Map.Entry::getKey)
                .toList();
    }
}