package com.angel.youthmeeting.controllers;

import com.angel.youthmeeting.models.dtos.AttendanceFiltersDTO;
import com.angel.youthmeeting.models.dtos.YouthMidLevelDTO;
import com.angel.youthmeeting.models.entities.Attendance;
import com.angel.youthmeeting.services.implementations.AttendanceService;
import com.angel.youthmeeting.util.security.EndPoints;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(EndPoints.ATTENDANCE)
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

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
        return ResponseEntity.status(HttpStatus.CREATED).body(attendanceService.addAttendance(meetingId, youthId));
    }

    @Operation(
            summary = "Use this api to get a report of the attendance of specific youths depending on some " +
                    "criteria of specific meetings depending on some criteria",
            description = "To filter the meetings to a specific criteria and then retrieve all the youths" +
                    " that attended each of those meetings and return list of youths that attended each on of the meetings")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The meetings retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "The user is unAuthenticated to request this endpoint")
    })
    @PostMapping(EndPoints.GET_ATTENDANCE_REPORT)
    @PreAuthorize("hasRole('ROLE_SERVANT_HEAD') or hasRole('ROLE_SERVANT')")
    public ResponseEntity<Map<Long,List<Attendance>>> getAttendanceReport(@Valid @RequestBody AttendanceFiltersDTO attendanceFiltersDTO){
        return ResponseEntity.status(HttpStatus.OK).body(attendanceService.getAttendanceReport(attendanceFiltersDTO));
    }

    @Operation(
            summary = "Use this api to get the youths that attend all the meetings which matches a specific criteria",
            description = "To filter the meetings to a specific criteria and then retrieve all the youths" +
                    " that attended all those meetings")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The meetings retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "The user is unAuthenticated to request this endpoint")
    })
    @PostMapping(EndPoints.GET_ATTENDANCE)
    @PreAuthorize("hasRole('ROLE_SERVANT_HEAD') or hasRole('ROLE_SERVANT')")
    public List<YouthMidLevelDTO> getAttendance(@Valid @RequestBody AttendanceFiltersDTO attendanceFiltersDTO){
        return attendanceService.findAttendanceOfAllMeetings(attendanceFiltersDTO);
    }

    @Operation(
            summary = "Use this api to get random number of youths that attended today's meeting which matches a specific criteria",
            description = "To filter the attendant youths to a specific criteria and then retrieve random number of them for the raffle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The youths retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "The user is unAuthenticated to request this endpoint")
    })
    @GetMapping(EndPoints.GET_RAFFLE)
    @PreAuthorize("hasRole('ROLE_SERVANT_HEAD') or hasRole('ROLE_SERVANT')")
    public ResponseEntity<List<YouthMidLevelDTO>> getRaffle(@Valid @RequestBody AttendanceFiltersDTO attendanceFiltersDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(attendanceService.getRaffle(attendanceFiltersDTO));
    }
}

