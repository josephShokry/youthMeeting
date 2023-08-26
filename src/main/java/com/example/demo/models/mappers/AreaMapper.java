package com.example.demo.models.mappers;

import com.example.demo.models.entities.Area;
import com.example.demo.models.DTOs.AreaDTO;
import com.example.demo.models.DTOs.LightDTO;
import org.mapstruct.*;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

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
