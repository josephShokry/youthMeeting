package com.example.demo.models.DTOs;

import com.example.demo.models.Youth;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class StreetDTO {
    @JsonProperty("id")
    public int id;
    @JsonProperty("streetName")
    public String streetName;
    public List<Youth> youthList;
}
