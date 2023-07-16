package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "youths")
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
    private int collegeLevel;
    @Column(name = "grad_level")
    private int gradLevel;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(referencedColumnName = "id")
    @JsonIgnore
    private Family family;
    @Column(name = "meeting_level")
    private int meetingLevel; // TODO check if we need 2 levels
    //    private Father father;
    @Column(name = "notes")
    private String notes;
}
