package com.example.demo.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "families")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Family {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "family_name")
    private String familyName;
    @Column(name = "family_level")
    private Integer familyLevel;
    @OneToMany(mappedBy = "family",orphanRemoval = true)
    private List<Youth> youthList;
    @OneToMany(mappedBy = "family",orphanRemoval = true)
    private List<Servant> servantList;
    @Column(name = "joining_year")
    private Integer joiningYear;

}
