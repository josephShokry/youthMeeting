package com.angel.youthmeeting.repositories;

import com.angel.youthmeeting.models.entities.Attendance;
import com.angel.youthmeeting.models.entities.Meeting;
import com.angel.youthmeeting.models.entities.Youth;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long>, JpaSpecificationExecutor<Attendance> {
    Boolean existsByMeetingAndYouth(Meeting meeting, Youth youth);

    @Override
    Page<Attendance> findAll(Specification<Attendance> specification, Pageable pageable);

    List<Attendance> findAll(Specification<Attendance> specification);
}
