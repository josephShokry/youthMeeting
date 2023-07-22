package com.example.demo.repositories;

import com.example.demo.models.DTOs.YouthFiltersDTO;
import com.example.demo.models.Youth;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class YouthSpecificationImpl implements YouthSpecification{
    private YouthFiltersDTO youthFiltersDTO;

    @Override
    public Predicate toPredicate(Root<Youth> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        // Add your filter criteria
        if(youthFiltersDTO != null && youthFiltersDTO.familyId != null)
            predicates.add(criteriaBuilder.equal(root.join("family", JoinType.LEFT).get("id"), youthFiltersDTO.familyId));
        if(youthFiltersDTO != null && youthFiltersDTO.streetId != null)
            predicates.add(criteriaBuilder.equal(root.join("street", JoinType.LEFT).get("id"), youthFiltersDTO.streetId));


        if(youthFiltersDTO != null && youthFiltersDTO.namePart != null)
            predicates.add(criteriaBuilder.like(criteriaBuilder.concat(root.get("firstName"), root.get("lastName")),
                "%" + youthFiltersDTO.namePart.toLowerCase() + "%"));
        if(youthFiltersDTO != null && youthFiltersDTO.fullDOB != null)
            predicates.add(criteriaBuilder.equal(root.get("dayOfBirth"),LocalDate.parse(youthFiltersDTO.fullDOB)));

        if(youthFiltersDTO != null && youthFiltersDTO.month != null){
            Expression<Integer> monthExpression = criteriaBuilder.function("MONTH", Integer.class, root.get("dayOfBirth"));
            predicates.add(criteriaBuilder.equal(monthExpression, youthFiltersDTO.month));
        }


        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
