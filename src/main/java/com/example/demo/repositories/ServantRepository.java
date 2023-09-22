package com.example.demo.repositories;

import com.example.demo.models.entities.Servant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServantRepository extends JpaRepository<Servant,Long> {
}
