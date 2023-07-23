package com.example.demo.models.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public class StreetDTO {
    @JsonProperty("id")
    public Integer id;
    @JsonProperty("streetName")
    @NotBlank(message = "please specify the street name")
    public String streetName;
}
