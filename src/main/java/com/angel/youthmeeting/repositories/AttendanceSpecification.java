package com.angel.youthmeeting.repositories;

import com.angel.youthmeeting.models.entities.Attendance;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public interface AttendanceSpecification extends Specification<Attendance> {

    Predicate toPredicate(Root<Attendance> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder);
}
