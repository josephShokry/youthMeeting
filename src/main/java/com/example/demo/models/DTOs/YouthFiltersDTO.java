package com.example.demo.models.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class YouthFiltersDTO {
    @JsonProperty("familyId")
    public Long familyId;
    @JsonProperty("streetId")
    public Long streetId;
    @JsonProperty("namePart")
    public String namePart;
    @JsonProperty("month")
    public Integer month;
    @JsonProperty("year")
    public Integer year;
    @JsonProperty("size")
    public Integer size;
    @JsonProperty("page")
    public Integer page;
}
