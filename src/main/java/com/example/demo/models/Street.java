package com.example.demo.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "streets")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Street {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "street_name")
    private String streetName;
    @OneToMany(mappedBy = "street",orphanRemoval = true)
    private List<Youth> youthList;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "area_id", referencedColumnName = "id")
//    @JsonIgnore
    private Area area;

}
