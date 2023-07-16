package com.example.demo.models.mappers;

import com.example.demo.models.Area;
import com.example.demo.models.DTOs.AreaDTO;
import com.example.demo.models.DTOs.LightDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public interface AreaMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Area getAreaFromDto(AreaDTO areaDTO, @MappingTarget Area area);
    @Mapping(source = "areaName", target = "name")
    LightDTO areaToLightDto(Area area, @MappingTarget LightDTO lightDTO);

    default Iterable<LightDTO> areasToLightDtos(Iterable<Area> areas){
        return StreamSupport.stream(areas.spliterator(), false)
                .map(entity -> areaToLightDto(entity,new LightDTO()))
                .collect(Collectors.toList());
    }
}
