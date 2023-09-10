package com.example.demo.models.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public class AreaDTO {
    @JsonProperty("Id")
    public Long id;
    @JsonProperty("areaName")
    @NotBlank(message = "please specify the area name")
    public String areaName;
}