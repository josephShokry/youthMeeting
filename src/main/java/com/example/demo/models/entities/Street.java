package com.example.demo.models.entities;

import jakarta.persistence.*;
import lombok.*;

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
    @Column(name = "name")
    private String name;
//    @OneToMany(mappedBy = "street",orphanRemoval = true)
//    private List<Youth> youthList;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "area_id", referencedColumnName = "id")
//    @JsonIgnore
    private Area area;

}
