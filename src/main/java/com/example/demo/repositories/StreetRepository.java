package com.example.demo.repositories;

import com.example.demo.models.Street;
import org.springframework.data.repository.CrudRepository;

public interface StreetRepository extends CrudRepository<Street,Integer> {
}
