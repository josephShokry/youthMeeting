package com.example.demo.models;

import com.example.demo.security.User;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Servant extends Person {
    @OneToOne
    private Family family;
}
