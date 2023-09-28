package com.angle.youthmeeting.repositories;

import com.angle.youthmeeting.models.entities.Servant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServantRepository extends JpaRepository<Servant,Long> {
}
