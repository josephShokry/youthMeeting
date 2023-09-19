package com.example.demo.repositories;

import com.example.demo.models.entities.Youth;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface YouthRepository extends JpaRepository<Youth,Long>, JpaSpecificationExecutor<Youth> {
    @Override
    Page<Youth> findAll(Specification<Youth> specification, Pageable pageable);
}
