package com.example.demo.repositories;

import com.example.demo.models.entities.Area;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AreaRepository extends JpaRepository<Area,Long> {
}
