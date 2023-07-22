package com.example.demo.models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
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
 //   private List<Servant> servantList;
    @Column(name = "joining_year")
    private Integer joiningYear;
}
