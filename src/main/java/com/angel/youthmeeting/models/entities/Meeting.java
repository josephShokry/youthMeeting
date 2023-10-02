package com.angel.youthmeeting.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Meeting extends BasicEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "topic")
    private String topic;

    @Column(name = "date", unique = true, nullable = false)
    private LocalDate date;

    //TODO: this should be basic person to include the fathers but basicPerson class is not entity so we can't now
    @ManyToOne
    @JoinColumn(name = "instructor_id", referencedColumnName = "id")
    private Servant instructor;

    @OneToMany
    @JoinColumn(name = "meeting_id" , referencedColumnName = "id")
    private List<Attendance> attendance;

//    public Boolean matches(MeetingFiltersDTO meetingFiltersDTO){
//        return (meetingFiltersDTO.getDay() == null || meetingFiltersDTO.getDay().equals(this.getDate().getDayOfMonth()))
//                && (meetingFiltersDTO.getMonth() == null || meetingFiltersDTO.getMonth().equals(this.getDate().getMonthValue()))
//                && (meetingFiltersDTO.getYear() == null || meetingFiltersDTO.getYear().equals(this.getDate().getYear()))
//                && (meetingFiltersDTO.getInstructorId() == null || meetingFiltersDTO.getInstructorId().equals(this.getInstructor().getId()))
//                && (meetingFiltersDTO.getTopic() == null || this.getTopic().contains(meetingFiltersDTO.getTopic()));
//    }
}
