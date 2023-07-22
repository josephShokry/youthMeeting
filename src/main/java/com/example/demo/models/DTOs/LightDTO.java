package com.example.demo.models.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LightDTO {
    @JsonProperty("name")
    public String name;
    @JsonProperty("Id")
    public Integer id;
}
