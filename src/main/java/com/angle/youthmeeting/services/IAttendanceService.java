package com.angle.youthmeeting.services;

import com.angle.youthmeeting.models.dtos.YouthFiltersDTO;
import com.angle.youthmeeting.models.entities.Youth;

import java.util.List;

public interface IAttendanceService {

    Boolean addAttendance(Long meetingId, Long youthId);

//    List<Youth> getAttendance(MeetingFiltersDTO meetingFiltersDTO, YouthFiltersDTO youthFiltersDTO);

    List<Youth> getRaffle(YouthFiltersDTO youthFiltersDTO, Integer numberOfPrizes);
}
