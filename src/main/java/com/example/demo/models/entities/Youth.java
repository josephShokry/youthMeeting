package com.example.demo.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Youth extends Person{
    @Column(name = "university")
    private String university;
    @Column(name = "college")
    private String college;
    @Column(name = "college_level")
    private Integer collegeLevel;
    @Column(name = "grad_level")
    private Integer gradLevel;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(referencedColumnName = "id", name = "family_id",unique = false)
    @JsonIgnore
    private Family family;
    @Column(name = "meeting_level")
    private Integer meetingLevel;
    @ManyToOne
    @JoinColumn(name = "father_id")
    private Father father;
    @Column(name = "notes")
    private String notes;
//    public Youth(Integer id, String firstName, String lastName, String dayOfBirth, String phoneNumber) {
//        super(id, firstName, lastName, LocalDate.parse(dayOfBirth), phoneNumber, null, null, null);
//    }
}
