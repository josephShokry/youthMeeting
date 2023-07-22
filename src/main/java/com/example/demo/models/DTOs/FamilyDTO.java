package com.example.demo.models.DTOs;

import com.example.demo.models.Servant;
import com.example.demo.models.Youth;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class FamilyDTO {
    @JsonProperty("familyName")
    @NotBlank(message = "please specify the family name")
    public String familyName;
    @JsonProperty("familyLevel")
    @Min(1)
    public Integer familyLevel;
    public List<Youth> youthList;
    public List<Servant> servantList;
    @JsonProperty("joiningYear")
    public Integer joiningYear;
    @JsonProperty("Id")
    public Integer id;

}
