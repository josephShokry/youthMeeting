package com.example.demo.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LightDTO {
    @JsonProperty("Id")
    private Long id;
    @JsonProperty("name")
    @NotBlank(message = "validation.error.name")
    private String name;
}
