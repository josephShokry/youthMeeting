package com.example.demo.models.mappers;

import com.example.demo.models.DTOs.LightDTO;
import com.example.demo.models.DTOs.StreetDTO;
import com.example.demo.models.Street;
import org.mapstruct.*;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface StreetMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Street getStreetFromDto(StreetDTO streetDTO, @MappingTarget Street street);
    @Mapping(source = "streetName", target = "name")
    LightDTO streetToLightDto(Street street, @MappingTarget LightDTO lightDTO);

    default Iterable<LightDTO> streetsToLightDtos(Iterable<Street> streets){
        return StreamSupport.stream(streets.spliterator(), false)
                .map(entity -> streetToLightDto(entity,new LightDTO()))
                .collect(Collectors.toList());
    }
}
