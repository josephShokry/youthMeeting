package com.example.demo.models;

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
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
    private String university;
    private String college;
    private int collegeLevel;
    private int gradLevel;
 //  private Family family;
//    private int meetingLevel; // TODO check if we need 2 levels
//    private Father father;
    private String notes;
}
