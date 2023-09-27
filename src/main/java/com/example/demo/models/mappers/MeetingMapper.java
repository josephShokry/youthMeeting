package com.example.demo.models.mappers;

import com.example.demo.models.dtos.MeetingDTO;
import com.example.demo.models.dtos.YouthDTO;
import com.example.demo.models.dtos.YouthMidLevelDTO;
import com.example.demo.models.entities.Meeting;
import com.example.demo.models.entities.Youth;
import com.example.demo.services.implementations.FamilyService;
import com.example.demo.services.implementations.FatherService;
import com.example.demo.services.implementations.ServantService;
import com.example.demo.services.implementations.StreetService;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MeetingMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "date", expression = "java(meetingDTO.getDate() != null ?" +
            " java.time.LocalDate.parse(meetingDTO.getDate()) : null)")
    Meeting mapMeetingDTO(MeetingDTO meetingDTO, @MappingTarget Meeting meeting, ServantService servantService);

    @AfterMapping
    default void attachEntities(MeetingDTO meetingDTO, @MappingTarget Meeting meeting, ServantService servantService){
        Optional.ofNullable(meetingDTO.getInstructorId()).ifPresent(
                instructorId -> meeting.setInstructor(servantService.getServantById(instructorId)));
    }

    @Mapping(target = "instructorId", source = "instructor.id")
    MeetingDTO mapMeeting(Meeting meeting, @MappingTarget MeetingDTO meetingDTO);

    List<MeetingDTO> mapListOfMeetings(List<Meeting> meetings);

    default Page<MeetingDTO> mapPageOfMeetings(Page<Meeting> meetingPage) {
        List<MeetingDTO> dtoList = mapListOfMeetings(meetingPage.getContent());
        return new PageImpl<>(dtoList, meetingPage.getPageable(), meetingPage.getTotalElements());
    }
}
