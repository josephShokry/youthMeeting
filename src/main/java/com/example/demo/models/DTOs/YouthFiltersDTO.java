package com.example.demo.models.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
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
    public Integer page;// TODO: put the paging in super class of this class
}
