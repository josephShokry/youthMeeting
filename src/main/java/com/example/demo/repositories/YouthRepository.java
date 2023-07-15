package com.example.demo.repositories;

import com.example.demo.models.Youth;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YouthRepository extends JpaRepository<Youth,Integer> {
    @Override
    Page<Youth> findAll(Pageable pageable);
}
