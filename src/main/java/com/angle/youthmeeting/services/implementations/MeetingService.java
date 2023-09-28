package com.angle.youthmeeting.services.implementations;

import com.angle.youthmeeting.exceptions.exceptions.DataNotFoundException;
import com.angle.youthmeeting.models.dtos.MeetingDTO;
import com.angle.youthmeeting.models.dtos.MeetingFiltersDTO;
import com.angle.youthmeeting.models.dtos.YouthFiltersDTO;
import com.angle.youthmeeting.models.entities.Meeting;
import com.angle.youthmeeting.models.entities.Youth;
import com.angle.youthmeeting.models.mappers.MeetingMapper;
import com.angle.youthmeeting.repositories.MeetingRepository;
import com.angle.youthmeeting.repositories.MeetingSpecificationImpl;
import com.angle.youthmeeting.services.IMeetingService;
import com.angle.youthmeeting.util.security.Filteration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

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

    @Override
    public Boolean addAttendance(Long meetingId, Long youthId) {
        Meeting meeting = findMeetingById(meetingId);
        meeting.getAttendance().add(youthService.findYouthById(youthId));
        meetingRepository.save(meeting);
        return true;
    }

    @Override
    public List<Youth> getAttendance(MeetingFiltersDTO meetingFiltersDTO, YouthFiltersDTO youthFiltersDTO) {
        Page<Meeting> meeting = findAll(meetingFiltersDTO);
        Set<Youth> common = new HashSet<>(meeting.getContent().get(0).getAttendance());
        for(int i = 1;i<meeting.getContent().size();i++){
            common.retainAll(meeting.getContent().get(i).getAttendance());
        }
        return Filteration.filter(common.stream().toList(), youthFiltersDTO);
    }

    @Override
    public List<Youth> getRaffle(YouthFiltersDTO youthFiltersDTO, Integer numberOfPrizes) {
        LocalDate today = LocalDate.now();
        // todo you can use db to get the meeting instead of filtering
        MeetingFiltersDTO meetingFiltersDTO = MeetingFiltersDTO.builder().day(today.getDayOfMonth()).month(today.getMonthValue()).year(today.getYear()).build();
        List<Youth> attendanceList = getAttendance(meetingFiltersDTO, youthFiltersDTO).stream().toList();
        IntStream winnersInd = ThreadLocalRandom.current().ints(0, attendanceList.size())
                .distinct().limit(numberOfPrizes);
        return winnersInd.mapToObj(attendanceList::get).toList();
    }
}
