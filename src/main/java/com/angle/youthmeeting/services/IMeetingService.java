package com.angle.youthmeeting.services;

import com.angle.youthmeeting.models.dtos.MeetingDTO;
import com.angle.youthmeeting.models.dtos.MeetingFiltersDTO;
import com.angle.youthmeeting.models.dtos.YouthFiltersDTO;
import com.angle.youthmeeting.models.entities.Meeting;
import com.angle.youthmeeting.models.entities.Youth;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IMeetingService {

    Long addMeeting(MeetingDTO meetingDTO);

    Meeting findMeetingById(Long meetingId);

    MeetingDTO findMeetingDtoById(Long meetingId);

    Page<MeetingDTO> findAllMeetingDTO(MeetingFiltersDTO meetingFiltersDTO);

    Page<Meeting> findAll(MeetingFiltersDTO meetingFiltersDTO);

    Boolean editMeeting(MeetingDTO meetingDTO);

    Boolean addAttendance(Long meetingId, Long youthId);

    List<Youth> getAttendance(MeetingFiltersDTO meetingFiltersDTO, YouthFiltersDTO youthFiltersDTO);

    List<Youth> getRaffle(YouthFiltersDTO youthFiltersDTO, Integer numberOfPrizes);
}
