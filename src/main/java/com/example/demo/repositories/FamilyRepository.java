package com.example.demo.repositories;

import com.example.demo.models.entities.Family;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyRepository extends CrudRepository<Family,Integer> {
}
