package com.example.demo.models.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class YouthFiltersDTO {
    @JsonProperty("familyId")
    public Integer familyId;
    @JsonProperty("streetId")
    public Integer streetId;
    @JsonProperty("namePart")
    public String namePart;
    @JsonProperty("month")
    public Integer month;
    @JsonProperty("fullDOB")
    public String fullDOB;
    @JsonProperty("size")
    public Integer size;
    @JsonProperty("page")
    public Integer page;



}
