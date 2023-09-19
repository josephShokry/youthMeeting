package com.example.demo.models.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class FamilyDTO { //TODO: try to extend the from the light DTO
    @JsonProperty("Id")
    public Long id;
    @JsonProperty("familyName")
    @NotBlank(message = "please specify the family name")
    public String familyName;
    @JsonProperty("familyLevel")
    @Min(1)
    public Integer familyLevel;
    @JsonProperty("joiningYear")
    public Integer joiningYear;

}
