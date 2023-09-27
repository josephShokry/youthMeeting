package com.example.demo.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "families")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Family extends BasicEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "family_level")
    private Integer familyLevel;

    @Column(name = "joining_year")
    private Integer joiningYear;
}
