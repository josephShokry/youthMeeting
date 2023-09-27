package com.example.demo.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
public class PaginationDTO {

    @JsonProperty("size")
    @Min(value = 1, message = "validation.error.pageSize.min")
    private Integer size;

    @JsonProperty("page")
    @Min(value = 1, message = "validation.error.pageNumber.min")
    private Integer page;
}
