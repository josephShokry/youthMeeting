package com.angel.youthmeeting.models.entities;

import com.angel.youthmeeting.models.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
public class Person extends BasicPerson {

    @Column(name = "day_of_birth")
    private LocalDate dayOfBirth;

    @Column(name = "building_number")
    private Integer buildingNumber;

    @Column(name = "floor")
    private String floor;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "street_id", referencedColumnName = "id")
    @JsonIgnore
    private Street street;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
}
