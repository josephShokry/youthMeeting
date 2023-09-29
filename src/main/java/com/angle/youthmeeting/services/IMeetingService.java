package com.angle.youthmeeting.services;

import com.angle.youthmeeting.models.dtos.MeetingDTO;
import com.angle.youthmeeting.models.dtos.MeetingFiltersDTO;
import com.angle.youthmeeting.models.entities.Meeting;
import org.springframework.data.domain.Page;

public interface IMeetingService {

    Long addMeeting(MeetingDTO meetingDTO);

    Meeting findMeetingById(Long meetingId);

    MeetingDTO findMeetingDtoById(Long meetingId);

    Page<MeetingDTO> findAllMeetingDTO(MeetingFiltersDTO meetingFiltersDTO);

    Page<Meeting> findAll(MeetingFiltersDTO meetingFiltersDTO);

    Boolean editMeeting(MeetingDTO meetingDTO);
}
