package com.angle.youthmeeting.repositories;

import com.angle.youthmeeting.models.entities.Father;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FatherRepository extends JpaRepository<Father,Long> {
}
