package com.example.demo.models.DTOs;

import com.example.demo.models.Youth;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class StreetDTO {
    @JsonProperty("id")
    public Integer id;
    @JsonProperty("streetName")
    @NotBlank(message = "please specify the street name")
    public String streetName;
    public List<Youth> youthList;
}
