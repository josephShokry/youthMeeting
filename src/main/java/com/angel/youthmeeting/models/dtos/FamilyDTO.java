package com.angel.youthmeeting.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class FamilyDTO extends LightDTO {

    @JsonProperty("familyLevel")
    @Min(value = 1, message = "validation.error.familyId.min")
    private Integer familyLevel;

    @JsonProperty("joiningYear")
    @NotNull(message = "validation.error.joiningYear")
    private Integer joiningYear;

}