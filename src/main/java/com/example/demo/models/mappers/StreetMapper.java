package com.example.demo.models.mappers;

import com.example.demo.models.DTOs.StreetDTO;
import com.example.demo.models.entities.Street;
import com.example.demo.services.implementations.AreaService;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface StreetMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Street streetDtoToStreet(StreetDTO streetDTO, @MappingTarget Street street, AreaService areaService);
    @AfterMapping
    default void attachArea(StreetDTO streetDTO, @MappingTarget Street street, AreaService areaService){
        street.setArea(areaService.findById(streetDTO.getAreaId()));
    }
}
