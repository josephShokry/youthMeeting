package com.angel.youthmeeting.services.implementations;

import com.angel.youthmeeting.models.dtos.MeetingFiltersDTO;
import com.angel.youthmeeting.models.dtos.YouthFiltersDTO;
import com.angel.youthmeeting.models.entities.Attendance;
import com.angel.youthmeeting.models.entities.Youth;
import com.angel.youthmeeting.repositories.AttendanceRepository;
import com.angel.youthmeeting.repositories.MeetingRepository;
import com.angel.youthmeeting.repositories.YouthSpecificationImpl;
import com.angel.youthmeeting.exceptions.exceptions.DuplicateAttendanceException;
import com.angel.youthmeeting.models.entities.Meeting;
import com.angel.youthmeeting.services.IAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@Service
public class AttendanceService implements IAttendanceService {

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private YouthService youthService;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private MeetingRepository meetingRepository;

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

//    @Override
    public List<Youth> getAttendance(MeetingFiltersDTO meetingFiltersDTO){//, YouthFiltersDTO youthFiltersDTO) {
        LocalTime fifteenMinutesAgo = LocalTime.now().minusMinutes(30);
        Page<Meeting> meetings = meetingService.findAll(meetingFiltersDTO);
        List<List<Youth>> youthsLists = meetings.getContent().stream()
                .map(Meeting::getAttendance)
                .map(meetingList -> meetingList.stream()
//                        .filter(attendance -> attendance.getTime().isBefore(fifteenMinutesAgo))
                        .map(Attendance::getYouth).toList())
                .toList();
        List<Youth> common = youthsLists.stream()
                .reduce((list1, list2) -> {
                    list1.retainAll(list2);
                    return list1;
                }).orElse(new ArrayList<>());

// Alternate solution
//        List<Youth> common = meetings.getContent().get(0).getAttendance().stream().map(Attendance::getYouth).toList();
//        for(int i = 1;i<meetings.getContent().size();i++){
//            common.retainAll(meetings.getContent().get(i).getAttendance().stream().map(Attendance::getYouth).toList());
//        }

        return common;
//        return Filteration.filter(common.stream().toList(), youthFiltersDTO);
    }

    @Override
    public List<Youth> getRaffle(YouthFiltersDTO youthFiltersDTO, Integer numberOfPrizes) {
        LocalDate today = LocalDate.now();
        MeetingFiltersDTO meetingFiltersDTO = MeetingFiltersDTO.builder().day(today.getDayOfMonth()).month(today.getMonthValue()).year(today.getYear()).build();
        List<Youth> attendanceList = getAttendance(meetingFiltersDTO/*, youthFiltersDTO*/).stream().toList();
        Specification<Youth> specification = new YouthSpecificationImpl(youthFiltersDTO);

        List<Youth> filtered = attendanceList.stream()
                .filter(entity -> specification.toPredicate(null,null, null).equals(entity))//.test(entity))
                .toList();


        IntStream winnersInd = ThreadLocalRandom.current().ints(0, filtered.size())
                .distinct().limit(Math.min(numberOfPrizes, filtered.size()));
        return winnersInd.mapToObj(filtered::get).toList();
    }
}
