package com.angel.youthmeeting.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
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
public class StreetDTO extends LightDTO{

    @JsonProperty("areaId")
    @Min(value = 1, message = "validation.error.areaId.min")
    private Long areaId;
}
