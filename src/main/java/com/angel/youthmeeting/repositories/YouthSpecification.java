package com.angel.youthmeeting.repositories;

import com.angel.youthmeeting.models.entities.Youth;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public interface YouthSpecification extends Specification<Youth> {

    Predicate toPredicate(Root<Youth> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder);
}
