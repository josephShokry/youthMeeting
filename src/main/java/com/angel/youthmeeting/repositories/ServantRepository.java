package com.angel.youthmeeting.repositories;

import com.angel.youthmeeting.models.entities.Servant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServantRepository extends JpaRepository<Servant,Long> {
}
