package com.example.demo.models.mappers;

import com.example.demo.models.DTOs.LightDTO;
import com.example.demo.models.entities.Area;
import com.example.demo.models.entities.Family;
import com.example.demo.models.entities.Street;
import com.example.demo.models.entities.Youth;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LightDTOMapper {
    LightDTO areaToLightDto(Area area, @MappingTarget LightDTO lightDTO);
    List<LightDTO> areasToLightDtos(List<Area> areas);

    LightDTO familyToLightDto(Family family, @MappingTarget LightDTO lightDTO);
    List<LightDTO> familiesToLightDtos(List<Family> families);
    @Mapping(expression = "java(youth.getFirstName() + ' ' + youth.getLastName())", target = "name")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    LightDTO youthToYouthLightDto(Youth youth, @MappingTarget LightDTO lightDTO);
    List<LightDTO> youthsToYouthLightDto(List<Youth> youths);
    LightDTO streetToLightDto(Street street, @MappingTarget LightDTO lightDTO);
    List<LightDTO> streetsToLightDtos(List<Street> streets);
}
