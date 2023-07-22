package com.example.demo.models.DTOs;

import com.example.demo.models.Servant;
import com.example.demo.models.Youth;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class FamilyDTO {
    @JsonProperty("familyName")
    public String familyName;
    @JsonProperty("familyLevel")
    public Integer familyLevel;
    public List<Youth> youthList;
    public List<Servant> servantList;
    @JsonProperty("joiningYear")
    public Integer joiningYear;
    @JsonProperty("Id")
    public Integer id;

}
