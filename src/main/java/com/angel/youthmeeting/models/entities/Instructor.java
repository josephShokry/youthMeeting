package com.angel.youthmeeting.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity(name = "instructors")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Instructor extends BasicEntity{

    @ManyToOne
    @JoinColumn(name = "servant_id", referencedColumnName = "id")
    private Servant servant;

    @ManyToOne
    @JoinColumn(name = "father_id", referencedColumnName = "id")
    private Father father;
}
