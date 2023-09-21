package com.example.demo.repositories;

import com.example.demo.models.entities.Street;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StreetRepository extends JpaRepository<Street,Long> {
}
