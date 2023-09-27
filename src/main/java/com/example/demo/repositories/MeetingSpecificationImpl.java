package com.example.demo.repositories;

import com.example.demo.models.dtos.MeetingFiltersDTO;
import com.example.demo.models.entities.Meeting;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class MeetingSpecificationImpl implements MeetingSpecification{
    private MeetingFiltersDTO meetingFiltersDTO;

    @Override
    public Predicate toPredicate(Root<Meeting> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if(meetingFiltersDTO != null){
            if(meetingFiltersDTO.getInstructorId() != null)
                predicates.add(criteriaBuilder.equal(root.join("instructor", JoinType.LEFT).get("id"), meetingFiltersDTO.getInstructorId()));
            if(meetingFiltersDTO.getTopic() != null)
                predicates.add(criteriaBuilder.like(root.get("topic"), "%" + meetingFiltersDTO.getTopic() + "%"));
            if(meetingFiltersDTO.getYear() != null){
                Expression<Integer> yearExpression = criteriaBuilder.function("YEAR", Integer.class, root.get("date"));
                predicates.add(criteriaBuilder.equal(yearExpression, meetingFiltersDTO.getYear()));
            }
            if(meetingFiltersDTO.getMonth() != null){
                Expression<Integer> monthExpression = criteriaBuilder.function("MONTH", Integer.class, root.get("date"));
                predicates.add(criteriaBuilder.equal(monthExpression, meetingFiltersDTO.getMonth()));
            }
            if(meetingFiltersDTO.getDay() != null){
                Expression<Integer> dayExpression = criteriaBuilder.function("DAY", Integer.class, root.get("date"));
                predicates.add(criteriaBuilder.equal(dayExpression, meetingFiltersDTO.getDay()));
            }

        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
