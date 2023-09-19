package com.example.demo.models.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FamilyDTO extends LightDTO {
    @JsonProperty("familyLevel")
    @Min(1)
    private Integer family_level;
    @JsonProperty("joiningYear")
    private Integer joiningYear;

}
