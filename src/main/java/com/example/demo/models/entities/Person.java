package com.example.demo.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "persons")
@SuperBuilder
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "day_of_birth")
    private LocalDate dayOfBirth;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "building number")
    private Integer buildingNumber;
    @Column(name = "floor")
    private String floor;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(referencedColumnName = "id", name = "street_id",unique = false)
    @JsonIgnore
    private Street street;
}
