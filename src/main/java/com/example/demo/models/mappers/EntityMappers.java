package com.example.demo.models.mappers;

import com.example.demo.models.Area;
import com.example.demo.models.DTOs.AreaDTO;
import com.example.demo.models.DTOs.StreetDTO;
import com.example.demo.models.Family;
import com.example.demo.models.Street;
import com.example.demo.models.Youth;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EntityMappers {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Area getAreaFromDto(AreaDTO areaDTO, @MappingTarget Area area);
    /////////////////////////////////////////////////////////////////////////////////
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Street getStreetFromDto(StreetDTO streetDTO, @MappingTarget Street street);
}
