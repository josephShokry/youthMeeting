package com.angle.youthmeeting.models.dtos;

import com.angle.youthmeeting.models.enums.Gender;
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

    @JsonProperty("gender")
    private Gender gender;
}
