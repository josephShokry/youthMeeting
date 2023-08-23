package com.example.demo.models.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServantDTO {
    public Integer id;
    public String firstName;
    public Integer familyId;
}
