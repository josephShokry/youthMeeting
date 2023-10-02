package com.angel.youthmeeting.repositories;

import com.angel.youthmeeting.models.dtos.YouthFiltersDTO;
import com.angel.youthmeeting.models.entities.Youth;
import com.angel.youthmeeting.util.security.Specifications;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class YouthSpecificationImpl implements YouthSpecification {

    private YouthFiltersDTO youthFiltersDTO;

    private Specifications<Youth> specifications;

    public YouthSpecificationImpl(YouthFiltersDTO youthFiltersDTO) {
        this.youthFiltersDTO = youthFiltersDTO;
    }

    @Override
    public Predicate toPredicate(Root<Youth> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        specifications = new Specifications<>(root, criteriaBuilder);
        List<Predicate> predicates = new ArrayList<>();
        Optional.ofNullable(youthFiltersDTO)
                .ifPresent(filters -> {
                    specifications.addFamilyFilter(filters.getFamilyId(), root, criteriaBuilder, predicates);
                    specifications.addStreetFilter(filters.getStreetId(), root, criteriaBuilder, predicates);
                    specifications.addNamePartFilter(filters.getNamePart(), root, criteriaBuilder, predicates);
                    specifications.addYearFilter(filters.getYear(), root, criteriaBuilder, predicates, "dayOfBirth");
                    specifications.addMonthFilter(filters.getMonth(), root, criteriaBuilder, predicates,"dayOfBirth");
                    specifications.addGenderFilter(filters.getGender(), root, criteriaBuilder, predicates);
                });
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}