package com.example.demo.models.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
public class YouthFiltersDTO extends PaginationDTO{
    @JsonProperty("familyId")
    private Long familyId;
    @JsonProperty("streetId")
    private Long streetId;
    @JsonProperty("namePart")
    private String namePart;
    @JsonProperty("month")
    private Integer month;
    @JsonProperty("year")
    private Integer year;
}
