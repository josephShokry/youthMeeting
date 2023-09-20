package com.example.demo.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StreetDTO extends LightDTO{
    @JsonProperty("areaId")
    @Min(value = 1, message = "validation.error.areaId.min")
    private Long areaId;
}
