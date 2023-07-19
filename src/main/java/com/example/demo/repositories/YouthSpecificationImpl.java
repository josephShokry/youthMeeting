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
    private String namePart;

    @Override
    public Predicate toPredicate(Root<Youth> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        // Add your filter criteria
        if(familyId != null)
            predicates.add(criteriaBuilder.equal(root.join("family", JoinType.LEFT).get("id"), familyId));
        if(streetId != null)
            predicates.add(criteriaBuilder.equal(root.join("street", JoinType.LEFT).get("id"), streetId));

        if(namePart != null)
        predicates.add(criteriaBuilder.like(criteriaBuilder.concat(root.get("firstName"), root.get("lastName")),
                "%" + namePart.toLowerCase() + "%"));




        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
