package com.example.demo.models.DTOs;

import com.example.demo.models.Street;
import com.example.demo.models.Youth;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AreaDTO {
    @JsonProperty("Id")
    public Integer id;
    @JsonProperty("areaName")
    public String areaName;
    public List<Street> streetList;
    public List<Youth> youthList;


}
