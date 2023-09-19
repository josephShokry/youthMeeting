package com.example.demo.models.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FamilyLightDTO { // TODO: delete this class
    @JsonProperty("Id")
    public Long id;
    @JsonProperty("familyName")
    public String familyName;
}
