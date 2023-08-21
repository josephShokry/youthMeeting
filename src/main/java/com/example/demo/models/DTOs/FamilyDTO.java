package com.example.demo.models.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FamilyDTO {
    @JsonProperty("familyName")
    @NotBlank(message = "please specify the family name")
    public String familyName;
    @JsonProperty("familyLevel")
    @Min(1)
    public Integer familyLevel;
    @JsonProperty("joiningYear")
    public Integer joiningYear;
    @JsonProperty("Id")
    public Integer id;

}
