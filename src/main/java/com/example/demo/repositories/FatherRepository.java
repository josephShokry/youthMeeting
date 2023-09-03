package com.example.demo.repositories;

import com.example.demo.models.entities.Father;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FatherRepository extends JpaRepository<Father,Integer> {
}
