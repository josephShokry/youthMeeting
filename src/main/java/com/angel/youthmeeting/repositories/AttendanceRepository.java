package com.angel.youthmeeting.repositories;

import com.angel.youthmeeting.models.entities.Attendance;
import com.angel.youthmeeting.models.entities.Meeting;
import com.angel.youthmeeting.models.entities.Youth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Boolean existsByMeetingAndYouth(Meeting meeting, Youth youth);
}
