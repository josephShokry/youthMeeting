package com.example.demo.controllers;

import com.example.demo.models.dtos.MeetingDTO;
import com.example.demo.models.dtos.MeetingFiltersDTO;
import com.example.demo.models.entities.Youth;
import com.example.demo.services.implementations.MeetingService;
import com.example.demo.util.security.EndPoints;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(EndPoints.MEETING)
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    @Operation(
            summary = "Use this api to add new meeting to the database",
            description = "To add new meeting to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The meeting is added successfully")
    })
    @PostMapping(EndPoints.ADD_MEETING)
    @PreAuthorize("hasRole('ROLE_SERVANT_HEAD')")
    ResponseEntity<Long> addMeeting(@RequestBody MeetingDTO meetingDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(meetingService.addMeeting(meetingDTO));
    }

    @Operation(
            summary = "Use this api to edit old meeting to the database",
            description = "To edit old meeting to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The meeting is added successfully")
    })
    @PatchMapping(EndPoints.Edit_MEETING)
    @PreAuthorize("hasRole('ROLE_SERVANT_HEAD')")
    ResponseEntity<Boolean> editMeeting(@RequestBody MeetingDTO meetingDTO){
        return ResponseEntity.status(HttpStatus.OK).body(meetingService.editMeeting(meetingDTO));
    }

    @Operation(
            summary = "Use this api to get meeting from the database",
            description = "To add get meeting from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The area is added successfully"),
            @ApiResponse(responseCode = "401", description = "The user is unAuthenticated to request this endpoint")
    })
    @GetMapping(EndPoints.GET_MEETING)
    @PreAuthorize("hasRole('ROLE_SERVANT_HEAD')")
    public ResponseEntity<MeetingDTO> getMeeting(@RequestParam Long meetingId) {
        return ResponseEntity.status(HttpStatus.OK).body(meetingService.findMeetingDtoById(meetingId));
    }

    @Operation(
            summary = "Use this api to add new youth attendance to a meeting",
            description = "To add new youth to the list of the attendance of a meeting")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The youth attendance is added successfully"),
            @ApiResponse(responseCode = "401", description = "The user is unAuthenticated to request this endpoint")
    })
    @PostMapping(EndPoints.ADD_ATTENDANCE)
    @PreAuthorize("hasRole('ROLE_SERVANT_HEAD') or hasRole('ROLE_SERVANT')")
    ResponseEntity<Boolean> addAttendance(@RequestParam Long meetingId, @RequestParam Long youthId){
        return ResponseEntity.status(HttpStatus.CREATED).body(meetingService.addAttendance(meetingId, youthId));
    }

    @Operation(
            summary = "Use this api to get & filter all meeting from the database",
            description = "To filter and retrieve all the meeting from the DB that matches a specific criteria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The meetings retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "The user is unAuthenticated to request this endpoint")
    })
    @GetMapping(EndPoints.GET_ALL)
    @PreAuthorize("hasRole('ROLE_SERVANT_HEAD')")
    public ResponseEntity<Page<MeetingDTO>> getAll(@Valid @RequestBody MeetingFiltersDTO meetingFiltersDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(meetingService.findAllMeetingDTO(meetingFiltersDTO));
    }

    @Operation(
            summary = "Use this api to get the youths that attend all the meetings which matches a specific criteria",
            description = "To filter the meetings to a specific criteria and then retrieve all the youths" +
                    " that attended all those meetings")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The meetings retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "The user is unAuthenticated to request this endpoint")
    })
    @GetMapping(EndPoints.GET_ATTENDANCE)
    @PreAuthorize("hasRole('ROLE_SERVANT_HEAD') or hasRole('ROLE_SERVANT')")
    public ResponseEntity<Set<Youth>> getAttendance(@Valid @RequestBody MeetingFiltersDTO meetingFiltersDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(meetingService.getAttendance(meetingFiltersDTO));
    }

}
