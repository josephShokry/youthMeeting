package com.example.demo.models.DTOs;

import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;

@NoArgsConstructor
public class YouthFiltersDTO {
    public Integer familyId;
    public Integer streetId;
    public String namePart;
    public Integer month;
    public String fullDOB;
    public Integer size = 10;
    public Integer page = 0;
}
