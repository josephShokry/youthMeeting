package com.angle.youthmeeting.util.security;

import com.angle.youthmeeting.models.dtos.MeetingFiltersDTO;
import com.angle.youthmeeting.models.dtos.YouthFiltersDTO;
import com.angle.youthmeeting.models.entities.Meeting;
import com.angle.youthmeeting.models.entities.Youth;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Filteration {

    public static List<Youth> filter(List<Youth> src, YouthFiltersDTO youthFiltersDTO){
        return src.stream()
                .filter(youth ->
                        (youthFiltersDTO.getFamilyId() == null || youthFiltersDTO.getFamilyId().equals(youth.getFamily().getId()))
                        && (youthFiltersDTO.getStreetId() == null || youthFiltersDTO.getStreetId().equals(youth.getStreet().getId()))
                        && (youthFiltersDTO.getNamePart() == null || (youth.getFirstName() + " " + youth.getLastName()).contains(youthFiltersDTO.getNamePart()))
                        && (youthFiltersDTO.getMonth() == null || youthFiltersDTO.getMonth().equals(youth.getDayOfBirth().getMonthValue()))
                        && (youthFiltersDTO.getYear() == null || youthFiltersDTO.getYear().equals(youth.getDayOfBirth().getYear()))
                        && (youthFiltersDTO.getGender() == null || youthFiltersDTO.getGender().equals(youth.getGender())))
                .toList();
    }

    public static List<Meeting> filter(List<Meeting> src, MeetingFiltersDTO meetingFiltersDTO){
        return src.stream()
                .filter(meeting ->
                           (meetingFiltersDTO.getDay() == null || meetingFiltersDTO.getDay().equals(meeting.getDate().getDayOfMonth()))
                        && (meetingFiltersDTO.getMonth() == null || meetingFiltersDTO.getMonth().equals(meeting.getDate().getMonthValue()))
                        && (meetingFiltersDTO.getYear() == null || meetingFiltersDTO.getYear().equals(meeting.getDate().getYear()))
                        && (meetingFiltersDTO.getInstructorId() == null || meetingFiltersDTO.getInstructorId().equals(meeting.getInstructor().getId()))
                        && (meetingFiltersDTO.getTopic() == null || meeting.getTopic().contains(meetingFiltersDTO.getTopic())))
                .toList();
    }

    private Filteration() {
    }
}
