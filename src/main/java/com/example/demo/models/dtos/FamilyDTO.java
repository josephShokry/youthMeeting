package com.example.demo.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class FamilyDTO extends LightDTO {
    @JsonProperty("familyLevel")
    @Min(value = 1, message = "validation.error.familyId.min")
    private Integer familyLevel;

    @JsonProperty("joiningYear")
    @NotNull(message = "validation.error.joiningYear")
    private Integer joiningYear;

}