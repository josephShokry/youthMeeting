package com.example.demo.models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
//@Builder
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
//    private Timestamp dayOfBirth;
    private String phoneNumber;

//    private Address address;

}
