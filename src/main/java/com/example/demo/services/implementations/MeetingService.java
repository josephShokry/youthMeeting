package com.example.demo.services.implementations;

import com.example.demo.exceptions.exceptions.DataNotFoundException;
import com.example.demo.models.dtos.MeetingDTO;
import com.example.demo.models.dtos.MeetingFiltersDTO;
//import com.example.demo.models.entities.BasicPerson;
//import com.example.demo.models.entities.Meeting;
//import com.example.demo.models.entities.Meeting;
//import com.example.demo.models.entities.Meeting;
//import com.example.demo.models.mappers.MeetingMapper;
import com.example.demo.models.dtos.YouthMidLevelDTO;
import com.example.demo.models.entities.Meeting;
import com.example.demo.models.entities.Youth;
import com.example.demo.models.mappers.MeetingMapper;
import com.example.demo.repositories.MeetingRepository;
import com.example.demo.repositories.MeetingSpecificationImpl;
import com.example.demo.services.IMeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
//    @Override
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

//    @Override
//    public List<BasicPerson> getTambola(Integer numberOfPrizes) {
//        //TODO: get the random people
//        return null;
//    }
//
    @Override
    public Boolean addAttendance(Long meetingId, Long youthId) {
        Meeting meeting = findMeetingById(meetingId);
        meeting.getAttendance().add(youthService.findYouthById(youthId));
        meetingRepository.save(meeting);
        return true;
    }

    @Override
    public Set<Youth> getAttendance(MeetingFiltersDTO meetingFiltersDTO) {
        Page<Meeting> meeting = findAll(meetingFiltersDTO);
        Set<Youth> common = new HashSet<>(meeting.getContent().get(0).getAttendance());
        for(int i = 1;i<meeting.getContent().size();i++){
            common.retainAll(meeting.getContent().get(i).getAttendance());
        }
        // TODO: get set of the intersection of all the sets inside this page
        return common;
    }
}
