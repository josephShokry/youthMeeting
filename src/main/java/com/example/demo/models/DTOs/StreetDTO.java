package com.example.demo.models.DTOs;

import com.example.demo.models.Area;
import com.example.demo.models.Youth;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

public class StreetDTO {
    @JsonProperty("id")
    public int Id;
    @JsonProperty("streetName")
    public String streetName;
    @JsonProperty("areaId")
    public int areaId;
    public List<Youth> youthList;
}
