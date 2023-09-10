package com.example.demo.repositories;

import com.example.demo.models.Street;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface StreetRepository extends JpaRepository<Street,Long> {
}
