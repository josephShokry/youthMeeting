package com.angel.youthmeeting.services.implementations;

import com.angel.youthmeeting.models.dtos.MeetingDTO;
import com.angel.youthmeeting.models.dtos.MeetingFiltersDTO;
import com.angel.youthmeeting.models.mappers.MeetingMapper;
import com.angel.youthmeeting.repositories.MeetingRepository;
import com.angel.youthmeeting.repositories.MeetingSpecificationImpl;
import com.angel.youthmeeting.exceptions.exceptions.DataNotFoundException;
import com.angel.youthmeeting.models.entities.Meeting;
import com.angel.youthmeeting.services.IMeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MeetingService implements IMeetingService {

    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private MeetingMapper meetingMapper;

    @Autowired
    private YouthService youthService;

    @Autowired
    private ServantService servantService;

    @Override
    public Long addMeeting(MeetingDTO meetingDTO) {
        Meeting meeting = new Meeting();
        meetingRepository.save(meetingMapper.mapMeetingDTO(meetingDTO, meeting, servantService));
        return meeting.getId();
    }

    @Override
    public Meeting findMeetingById(Long meetingId) {
        meetingId = Optional.ofNullable(meetingId).orElseThrow(() -> new DataNotFoundException("validation.error.meetingId"));
        return meetingRepository.findById(meetingId).orElseThrow(
                () -> new DataNotFoundException("validation.error.meeting"));
    }

    @Override
    public MeetingDTO findMeetingDtoById(Long meetingId) {
        return meetingMapper.mapMeeting(findMeetingById(meetingId), new MeetingDTO());
    }

    @Override
    public Page<MeetingDTO> findAllMeetingDTO(MeetingFiltersDTO meetingFiltersDTO) {
        Page<Meeting> meetings = findAll(meetingFiltersDTO);
        return meetingMapper.mapPageOfMeetings(meetings);
    }

    @Override
    public Page<Meeting> findAll(MeetingFiltersDTO meetingFiltersDTO) {
        meetingFiltersDTO.setPage(Optional.ofNullable(meetingFiltersDTO.getPage()).map(page -> page - 1).orElse(0));
        meetingFiltersDTO.setSize(Optional.ofNullable(meetingFiltersDTO.getSize()).orElse(10));
        Pageable paging = PageRequest.of(meetingFiltersDTO.getPage(), meetingFiltersDTO.getSize());
        Specification<Meeting> specification = new MeetingSpecificationImpl(meetingFiltersDTO);
        return meetingRepository.findAll(specification, paging);
    }

    @Override
    public Boolean editMeeting(MeetingDTO meetingDTO) {
        Meeting meeting = findMeetingById(meetingDTO.getId());
        meetingRepository.save(meetingMapper.mapMeetingDTO(meetingDTO, meeting, servantService));
        return true;
    }
}
