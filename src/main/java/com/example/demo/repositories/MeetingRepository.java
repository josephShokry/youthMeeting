package com.example.demo.repositories;

import com.example.demo.models.entities.Meeting;
import com.example.demo.models.entities.Youth;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long>, JpaSpecificationExecutor<Meeting> {
    @Override
    Page<Meeting> findAll(Specification<Meeting> specification, Pageable pageable);
}
