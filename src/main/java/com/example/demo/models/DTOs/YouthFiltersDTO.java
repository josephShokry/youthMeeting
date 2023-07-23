package com.example.demo.models.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class YouthFiltersDTO {
    public Integer familyId;
    public Integer streetId;
    public String namePart;
    public Integer month;
    public String fullDOB;
    public Integer size;
    public Integer page;



}
