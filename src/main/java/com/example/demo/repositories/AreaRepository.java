package com.example.demo.repositories;

import com.example.demo.models.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AreaRepository extends JpaRepository<Area,Long> {
}
