package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Youth extends Person{
    @Column(name = "university")
    private String university;
    @Column(name = "college")
    private String college;
    @Column(name = "college_level")
    private String collegeLevel;
    @Column(name = "grad_level")
    private Integer gradLevel;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "family_id", referencedColumnName = "id")
    @JsonIgnore
    private Family family;
    @Column(name = "meeting_level")
    private Integer meetingLevel;
//    private Father father;
    @Column(name = "notes")
    private String notes;
    public Youth(Long id, String firstName, String lastName, String dayOfBirth, String phoneNumber) {
        super(id, firstName, lastName, LocalDate.parse(dayOfBirth), phoneNumber, null, null, null);
    }
}