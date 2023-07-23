package com.example.demo.models.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LightDTO {
    @JsonProperty("Id")
    public Integer id;
    @JsonProperty("name")
    public String name;
}
