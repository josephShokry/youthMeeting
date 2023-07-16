package com.example.demo.models.mappers;

import com.example.demo.models.DTOs.StreetDTO;
import com.example.demo.models.Street;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

public interface StreetMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Street getStreetFromDto(StreetDTO streetDTO, @MappingTarget Street street);
}
