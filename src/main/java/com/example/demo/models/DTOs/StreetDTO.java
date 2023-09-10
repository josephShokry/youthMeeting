package com.example.demo.models.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class StreetDTO {
    @JsonProperty("id")
    public Long id;
    @JsonProperty("streetName")
    @NotBlank(message = "please specify the street name")
    public String streetName;
    @JsonProperty("areaId")
    @Min(1)
    public Long areaId;
}
