package com.example.demo.repositories;

import com.example.demo.models.Youth;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class YouthSpecificationImpl implements YouthSpecification{
    private Integer familyId;
    private Integer streetId;

    @Override
    public Predicate toPredicate(Root<Youth> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        // Add your filter criteria
        if(familyId != null)
            predicates.add(criteriaBuilder.equal(root.join("family", JoinType.LEFT).get("id"), familyId));
        if(streetId != null)
            predicates.add(criteriaBuilder.equal(root.join("street", JoinType.LEFT).get("id"), streetId));

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
