package com.example.demo.models.mappers;

import com.example.demo.models.DTOs.LightDTO;
import com.example.demo.models.Youth;
import org.mapstruct.*;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LightYouthMapper {
    @Mapping(expression = "java(youth.getFirstName() + ' ' + youth.getLastName())", target = "name")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    LightDTO youthToYouthLightDto(Youth youth, @MappingTarget LightDTO lightDTO);
    default Iterable<LightDTO> youthsToYouthLightDto(Iterable<Youth> youths){
        return StreamSupport.stream(youths.spliterator(), false)
                .map(entity -> youthToYouthLightDto(entity,new LightDTO()))
                .collect(Collectors.toList());
    }
}
