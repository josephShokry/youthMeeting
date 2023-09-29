package com.angle.youthmeeting.util.security;

import com.angle.youthmeeting.models.enums.Gender;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class Specifications<T> {

    private final Root<T> root;
    private final CriteriaBuilder criteriaBuilder;
    public void addFamilyFilter(Long familyId, Root<T> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates) {
        if (familyId != null) {
            predicates.add(criteriaBuilder.equal(root.join("family", JoinType.LEFT).get("id"), familyId));
        }
    }

    public void addStreetFilter(Long streetId, Root<T> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates) {
        if (streetId != null) {
            predicates.add(criteriaBuilder.equal(root.join("street", JoinType.LEFT).get("id"), streetId));
        }
    }

    public void addNamePartFilter(String namePart, Root<T> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates) {
        if (namePart != null) {
            Expression<String> fullName = criteriaBuilder.concat(
                    criteriaBuilder.concat(root.get("firstName"), " "),
                    root.get("lastName")
            );
            predicates.add(criteriaBuilder.like(fullName, "%" + namePart + "%"));
        }
    }

    public void addYearFilter(Integer year, Root<T> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, String propertyName) {
        if (year != null) {
            Expression<Integer> yearExpression = criteriaBuilder.function("YEAR", Integer.class, root.get(propertyName));
            predicates.add(criteriaBuilder.equal(yearExpression, year));
        }
    }

    public void addMonthFilter(Integer month, Root<T> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, String propertyName) {
        if (month != null) {
            Expression<Integer> monthExpression = criteriaBuilder.function("MONTH", Integer.class, root.get(propertyName));
            predicates.add(criteriaBuilder.equal(monthExpression, month));
        }
    }

    public void addGenderFilter(Gender gender, Root<T> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates) {
        if (gender != null) {
            predicates.add(criteriaBuilder.equal(root.get("gender"), gender));
        }
    }

    public void addInstructorFilter(Long instructorId, Root<T> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates) {
        if (instructorId != null) {
            predicates.add(criteriaBuilder.equal(root.join("instructor", JoinType.LEFT).get("id"), instructorId));
        }
    }

    public void addTopicFilter(String topic, Root<T> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates) {
        if (topic != null) {
            predicates.add(criteriaBuilder.like(root.get("topic"), "%" + topic + "%"));
        }
    }

    public void addDayFilter(Integer day, Root<T> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, String propertyName) {
        if (day != null) {
            Expression<Integer> dayExpression = criteriaBuilder.function("DAY", Integer.class, root.get(propertyName));
            predicates.add(criteriaBuilder.equal(dayExpression, day));
        }
    }
}

