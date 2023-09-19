package com.example.demo.models.mappers;

import com.example.demo.models.DTOs.LightDTO;
import com.example.demo.models.DTOs.StreetDTO;
import com.example.demo.models.entities.Street;
import com.example.demo.services.implementations.AreaService;
import org.mapstruct.*;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface StreetMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Street streetDtoToStreet(StreetDTO streetDTO, @MappingTarget Street street, AreaService areaService);// TODO: removed @context
    @AfterMapping
    default void attachArea(StreetDTO streetDTO, @MappingTarget Street street, AreaService areaService){
        street.setArea(areaService.findById(streetDTO.areaId));
    }
    @Mapping(source = "streetName", target = "name")
    LightDTO streetToLightDto(Street street, @MappingTarget LightDTO lightDTO);

    default Iterable<LightDTO> streetsToLightDtos(Iterable<Street> streets){
        return StreamSupport.stream(streets.spliterator(), false)
                .map(entity -> streetToLightDto(entity,new LightDTO()))
                .collect(Collectors.toList());
    }
}
