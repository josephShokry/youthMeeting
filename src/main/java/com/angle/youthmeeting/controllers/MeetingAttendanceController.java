package com.angle.youthmeeting.controllers;

import com.angle.youthmeeting.models.dtos.MeetingFiltersDTO;
import com.angle.youthmeeting.models.dtos.YouthFiltersDTO;
import com.angle.youthmeeting.models.entities.Youth;
import com.angle.youthmeeting.services.implementations.AttendanceService;
import com.angle.youthmeeting.util.security.EndPoints;
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

@RestController
@RequestMapping(EndPoints.ATTENDANCE)
public class MeetingAttendanceController {

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
            summary = "Use this api to get the youths that attend all the meetings which matches a specific criteria",
            description = "To filter the meetings to a specific criteria and then retrieve all the youths" +
                    " that attended all those meetings")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The meetings retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "The user is unAuthenticated to request this endpoint")
    })
    @GetMapping(EndPoints.GET_ATTENDANCE)
    @PreAuthorize("hasRole('ROLE_SERVANT_HEAD') or hasRole('ROLE_SERVANT')")
    public ResponseEntity<List<Youth>> getAttendance(@Valid @RequestBody MeetingFiltersDTO meetingFiltersDTO){//,
//                                                     @Valid @RequestBody YouthFiltersDTO youthFiltersDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(attendanceService.getAttendance(meetingFiltersDTO));//, youthFiltersDTO));
    }

    // TODO: you can make and endpoint that takes list of ids and filtering criteries and return smaller list

    @Operation(
            summary = "Use this api to get random number of youths that attended today's meeting which matches a specific criteria",
            description = "To filter the attendant youths to a specific criteria and then retrieve random number of them for the raffle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The youths retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "The user is unAuthenticated to request this endpoint")
    })
    @GetMapping(EndPoints.GET_RAFFLE)
    @PreAuthorize("hasRole('ROLE_SERVANT_HEAD') or hasRole('ROLE_SERVANT')")
    public ResponseEntity<List<Youth>> getRaffle(@Valid @RequestBody YouthFiltersDTO youthFiltersDTO,
                                                 @RequestParam Integer numberOfPrizes) {
        return ResponseEntity.status(HttpStatus.OK).body(attendanceService.getRaffle(youthFiltersDTO, numberOfPrizes));
    }
}
