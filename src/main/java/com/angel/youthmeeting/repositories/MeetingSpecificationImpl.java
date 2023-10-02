package com.angel.youthmeeting.repositories;

import com.angel.youthmeeting.models.dtos.MeetingFiltersDTO;
import com.angel.youthmeeting.models.entities.Meeting;
import com.angel.youthmeeting.util.security.Specifications;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MeetingSpecificationImpl implements MeetingSpecification{

    private MeetingFiltersDTO meetingFiltersDTO;

    private Specifications<Meeting> specifications;

    public MeetingSpecificationImpl(MeetingFiltersDTO meetingFiltersDTO) {
        this.meetingFiltersDTO = meetingFiltersDTO;
    }

    @Override
    public Predicate toPredicate(Root<Meeting> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        specifications = new Specifications<>(root, criteriaBuilder);
        List<Predicate> predicates = new ArrayList<>();
        Optional.ofNullable(meetingFiltersDTO)
                .ifPresent(filters -> {
                    specifications.addInstructorFilter(filters.getInstructorId(), root, criteriaBuilder, predicates);
                    specifications.addTopicFilter(filters.getTopic(), root, criteriaBuilder, predicates);
                    specifications.addYearFilter(filters.getYear(), root, criteriaBuilder, predicates,"date");
                    specifications.addMonthFilter(filters.getMonth(), root, criteriaBuilder, predicates,"date");
                    specifications.addDayFilter(filters.getDay(), root, criteriaBuilder, predicates,"date");
                });
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}