package com.example.demo.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "areas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Area {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String areaName;
    @OneToMany(mappedBy = "area",orphanRemoval = true)
    private List<Street> streetList;
    @OneToMany(mappedBy = "area",orphanRemoval = true)
    private List<Youth> youthList;
}
