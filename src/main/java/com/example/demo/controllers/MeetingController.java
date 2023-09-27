package com.example.demo.controllers;

import com.example.demo.models.dtos.MeetingDTO;
import com.example.demo.models.dtos.MeetingFiltersDTO;
import com.example.demo.models.entities.BasicPerson;
import com.example.demo.models.entities.Youth;
import com.example.demo.services.implementations.MeetingService;
import com.example.demo.util.security.EndPoints;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(EndPoints.MEETING)
public class MeetingController {
    @Autowired
    private MeetingService meetingService;

    @PostMapping(EndPoints.ADD_MEETING)
    ResponseEntity<Long> addMeeting(@RequestBody MeetingDTO meetingDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(meetingService.addMeeting(meetingDTO));
    }

    @GetMapping(EndPoints.GET_MEETING)
    public ResponseEntity<MeetingDTO> getMeeting(@RequestParam Long meetingId) {
        return ResponseEntity.status(HttpStatus.OK).body(meetingService.findMeetingDtoById(meetingId));
    }

    @PostMapping(EndPoints.ADD_ATTENDANCE)
    ResponseEntity<Boolean> addAttendance(@RequestParam Long meetingId, @RequestParam Long youthId){
        return ResponseEntity.status(HttpStatus.CREATED).body(meetingService.addAttendance(meetingId, youthId));
    }

    @GetMapping(EndPoints.GET_ALL)
    public ResponseEntity<Page<MeetingDTO>> getAll(@Valid @RequestBody MeetingFiltersDTO meetingFiltersDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(meetingService.findAllMeetingDTO(meetingFiltersDTO));
    }


    @GetMapping(EndPoints.GET_ATTENDANCE)
    public ResponseEntity<Set<Youth>> getAttendance(@Valid @RequestBody MeetingFiltersDTO meetingFiltersDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(meetingService.getAttendance(meetingFiltersDTO));
    }

//    @GetMapping(EndPoints.GET_TAMBOLA)
//    public ResponseEntity<List<BasicPerson>> getTombola(@RequestParam Integer numberOfPrizes) {
//        return ResponseEntity.status(HttpStatus.OK).body(meetingService.getTambola(numberOfPrizes));
//    }

}
