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
    private int id;
    private String familyName;
    private int familyLevel;
 //   private List<Youth> youthList;
 //   private List<Servant> servantList;
    private int joiningYear;
}
