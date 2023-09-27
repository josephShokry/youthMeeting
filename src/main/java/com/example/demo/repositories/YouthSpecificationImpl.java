package com.example.demo.repositories;

import com.example.demo.models.dtos.YouthFiltersDTO;
import com.example.demo.models.entities.Youth;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class YouthSpecificationImpl implements YouthSpecification{

    private YouthFiltersDTO youthFiltersDTO;

    @Override
    public Predicate toPredicate(Root<Youth> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if(youthFiltersDTO != null){
            if(youthFiltersDTO.getFamilyId() != null)
                predicates.add(criteriaBuilder.equal(root.join("family", JoinType.LEFT).get("id"), youthFiltersDTO.getFamilyId()));
            if(youthFiltersDTO.getStreetId() != null)
                predicates.add(criteriaBuilder.equal(root.join("street", JoinType.LEFT).get("id"), youthFiltersDTO.getStreetId()));
            if(youthFiltersDTO.getNamePart() != null)
                predicates.add(criteriaBuilder.like(criteriaBuilder.concat(criteriaBuilder.concat(
                        root.get("firstName"), " "), root.get("lastName")), "%" + youthFiltersDTO.getNamePart() + "%"));
            if(youthFiltersDTO.getYear() != null){
                Expression<Integer> yearExpression = criteriaBuilder.function("YEAR", Integer.class, root.get("dayOfBirth"));
                predicates.add(criteriaBuilder.equal(yearExpression, youthFiltersDTO.getYear()));
            }
            if(youthFiltersDTO.getMonth() != null){
                Expression<Integer> monthExpression = criteriaBuilder.function("MONTH", Integer.class, root.get("dayOfBirth"));
                predicates.add(criteriaBuilder.equal(monthExpression, youthFiltersDTO.getMonth()));
            }
            if(youthFiltersDTO.getGender() != null){
                predicates.add(criteriaBuilder.equal(root.get("gender"), youthFiltersDTO.getGender()));
            }
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
