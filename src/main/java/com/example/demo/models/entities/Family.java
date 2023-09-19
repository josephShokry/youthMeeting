package com.example.demo.models.entities;

import jakarta.persistence.*;
import lombok.*;

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
    private Long id;
    @Column(name = "family_name")
    private String familyName;
    @Column(name = "family_level")
    private Integer familyLevel;
//    @OneToMany(mappedBy = "family",orphanRemoval = true)
//    private List<Youth> youthList;
 //   private List<Servant> servantList;
    @Column(name = "joining_year")
    private Integer joiningYear;
}
