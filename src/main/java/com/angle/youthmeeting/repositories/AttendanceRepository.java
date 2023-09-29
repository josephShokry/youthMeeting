package com.angle.youthmeeting.repositories;

import com.angle.youthmeeting.models.entities.Attendance;
import com.angle.youthmeeting.models.entities.Meeting;
import com.angle.youthmeeting.models.entities.Youth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Boolean existsByMeetingAndYouth(Meeting meeting, Youth youth);
}
