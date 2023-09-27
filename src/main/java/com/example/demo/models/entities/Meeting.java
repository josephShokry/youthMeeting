package com.example.demo.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Meeting extends BasicEntity{
    private String name;
    private String topic;
    private LocalDate date;
    //TODO: this should be basic person to include the fathers but basicPerson class is not entity so we can't now
    @ManyToOne
    private Servant instructor;
    @ManyToMany
    private Set<Youth> attendance;
}
