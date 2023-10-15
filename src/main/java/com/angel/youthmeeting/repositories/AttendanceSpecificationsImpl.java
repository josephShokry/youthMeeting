package com.angel.youthmeeting.repositories;

import com.angel.youthmeeting.models.dtos.AttendanceFiltersDTO;
import com.angel.youthmeeting.models.entities.Attendance;
import com.angel.youthmeeting.models.entities.Meeting;
import com.angel.youthmeeting.models.entities.Youth;
import com.angel.youthmeeting.util.security.Specifications;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AttendanceSpecificationsImpl implements AttendanceSpecification{
    private AttendanceFiltersDTO attendanceFiltersDTO;

    public AttendanceSpecificationsImpl(AttendanceFiltersDTO attendanceFiltersDTO) {
        this.attendanceFiltersDTO = attendanceFiltersDTO;
    }

    public Predicate toPredicate(Root<Attendance> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Specifications<Attendance> specifications = new Specifications<>(root, criteriaBuilder);
        List<Predicate> predicates = new ArrayList<>();
        Join<Youth, Attendance> youthAttendanceJoin = root.join("youth");
        Join<Meeting, Attendance> meetingAttendanceJoin = root.join("meeting", JoinType.LEFT);
        Optional.ofNullable(attendanceFiltersDTO)
                .ifPresent(filters -> {
                    specifications.addFamilyFilter(filters.getFamilyId(), youthAttendanceJoin, criteriaBuilder, predicates);
                    specifications.addNamePartFilter(attendanceFiltersDTO.getYouthNamePart(), youthAttendanceJoin , criteriaBuilder, predicates);
                    specifications.addGenderFilter(filters.getGender(), youthAttendanceJoin, criteriaBuilder, predicates);
                    specifications.addYearFilter(filters.getMeetingYear(), meetingAttendanceJoin, criteriaBuilder, predicates,"date");
                    specifications.addMonthFilter(filters.getMeetingMonth(), meetingAttendanceJoin, criteriaBuilder, predicates,"date");
                    specifications.addDayFilter(filters.getMeetingDay(), meetingAttendanceJoin, criteriaBuilder, predicates,"date");
                });
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }


}
