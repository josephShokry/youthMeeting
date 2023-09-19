package com.example.demo.models.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "areas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "area_name")
    private String areaName;
//    @OneToMany(mappedBy = "area",orphanRemoval = true)
//    private List<Street> streets;
}
