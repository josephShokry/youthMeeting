package com.example.demo.models.DTOs;

import com.example.demo.models.Area;
import com.example.demo.models.Youth;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

public class StreetDTO {

    public int Id;
    public String streetName;

    public int areaId;
    public List<Youth> youthList;
}
