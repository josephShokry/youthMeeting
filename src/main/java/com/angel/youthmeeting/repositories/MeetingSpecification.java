package com.angel.youthmeeting.repositories;

import com.angel.youthmeeting.models.entities.Meeting;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public interface MeetingSpecification extends Specification<Meeting> {

    Predicate toPredicate(Root<Meeting> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder);
}
