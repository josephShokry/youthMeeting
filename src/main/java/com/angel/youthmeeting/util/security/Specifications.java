package com.angel.youthmeeting.util.security;

import com.angel.youthmeeting.models.entities.Family;
import com.angel.youthmeeting.models.entities.Youth;
import com.angel.youthmeeting.models.enums.Gender;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class Specifications<T> {

    private final From<?, T> root;
    private final CriteriaBuilder criteriaBuilder;
    public void addFamilyFilter(Long familyId, From<?, T> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates) {
        if (familyId != null) {
            //TODO:do i need to separate this method in 2 lines
            Join<Family, Youth> familyYouthJoin = root.join("family");
            predicates.add(criteriaBuilder.equal(familyYouthJoin.get("id"), familyId));
        }
    }

    public void addStreetFilter(Long streetId, From<?, T> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates) {
        if (streetId != null) {
            predicates.add(criteriaBuilder.equal(root.join("street", JoinType.LEFT).get("id"), streetId));
        }
    }

    public void addNamePartFilter(String namePart, From<?, T> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates) {
        if (namePart != null) {
            Expression<String> fullName = criteriaBuilder.concat(
                    criteriaBuilder.concat(root.get("firstName"), " "),
                    root.get("lastName")
            );
            predicates.add(criteriaBuilder.like(fullName, "%" + namePart + "%"));
        }
    }

    public void addYearFilter(Integer year, From<?, T> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, String propertyName) {
        if (year != null) {
            Expression<Integer> yearExpression = criteriaBuilder.function("YEAR", Integer.class, root.get(propertyName));
            predicates.add(criteriaBuilder.equal(yearExpression, year));
        }
    }

    public void addMonthFilter(Integer month, From<?, T> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, String propertyName) {
        if (month != null) {
            Expression<Integer> monthExpression = criteriaBuilder.function("MONTH", Integer.class, root.get(propertyName));
            predicates.add(criteriaBuilder.equal(monthExpression, month));
        }
    }

    public void addGenderFilter(Gender gender, From<?, T> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates) {
        if (gender != null) {
            predicates.add(criteriaBuilder.equal(root.get("gender"), gender));
        }
    }

    public void addInstructorFilter(Long instructorId, From<?, T> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates) {
        if (instructorId != null) {
            predicates.add(criteriaBuilder.equal(root.join("instructor", JoinType.LEFT).get("id"), instructorId));
        }
    }

    public void addTopicFilter(String topic, From<?, T> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates) {
        if (topic != null) {
            predicates.add(criteriaBuilder.like(root.get("topic"), "%" + topic + "%"));
        }
    }

    public void addDayFilter(Integer day, From<?, T> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, String propertyName) {
        if (day != null) {
            Expression<Integer> dayExpression = criteriaBuilder.function("DAY", Integer.class, root.get(propertyName));
            predicates.add(criteriaBuilder.equal(dayExpression, day));
        }
    }
}

