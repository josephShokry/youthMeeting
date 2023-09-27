package com.example.demo.services;

import com.example.demo.models.dtos.MeetingDTO;
import com.example.demo.models.dtos.MeetingFiltersDTO;
//import com.example.demo.models.entities.BasicPerson;
import com.example.demo.models.entities.Meeting;
import com.example.demo.models.entities.Youth;
import org.springframework.data.domain.Page;

import java.util.Set;

public interface IMeetingService {
    Long addMeeting(MeetingDTO meetingDTO);
    Meeting findMeetingById(Long meetingId);
    MeetingDTO findMeetingDtoById(Long meetingId);
    Page<MeetingDTO> findAllMeetingDTO(MeetingFiltersDTO meetingFiltersDTO);
    Boolean editMeeting(MeetingDTO meetingDTO);
//    List<BasicPerson> getTambola(Integer numberOfPrizes);
    Boolean addAttendance(Long meetingId, Long youthId);
    public Set<Youth> getAttendance(MeetingFiltersDTO meetingFiltersDTO);
}
