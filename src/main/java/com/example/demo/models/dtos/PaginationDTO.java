package com.example.demo.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
public class PaginationDTO {
    @JsonProperty("size")
    private Integer size;
    @JsonProperty("page")
    private Integer page;
}
