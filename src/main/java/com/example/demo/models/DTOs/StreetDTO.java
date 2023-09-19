package com.example.demo.models.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StreetDTO extends LightDTO{
    @JsonProperty("areaId")
    @Min(1)
    private Long areaId;
}
