package com.example.demo.models.entities;

import java.util.List;
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
    @Column(name = "name")
    private String name;
    @Column(name = "family_level")
    private Integer familyLevel;
    @Column(name = "joining_year")
    private Integer joiningYear;
    @OneToMany(mappedBy = "family",orphanRemoval = true)
    private List<Servant> servantList;
}
