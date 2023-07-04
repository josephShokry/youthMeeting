package com.example.demo.repositories;

import com.example.demo.models.Youth;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YouthRepository extends CrudRepository<Youth,Integer> {

}
