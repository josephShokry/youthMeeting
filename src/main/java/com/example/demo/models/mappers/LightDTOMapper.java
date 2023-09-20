package com.example.demo.models.mappers;

import com.example.demo.models.dtos.LightDTO;
import com.example.demo.models.entities.Area;
import com.example.demo.models.entities.Family;
import com.example.demo.models.entities.Street;
import com.example.demo.models.entities.Youth;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LightDTOMapper {
    LightDTO mapArea(Area area, @MappingTarget LightDTO lightDTO);
    List<LightDTO> mapListOfAreas(List<Area> areas);

    LightDTO mapFamily(Family family, @MappingTarget LightDTO lightDTO);
    List<LightDTO> mapListOfFamilies(List<Family> families);
    @Mapping(expression = "java(youth.getFirstName() + ' ' + youth.getLastName())", target = "name")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    LightDTO mapYouth(Youth youth, @MappingTarget LightDTO lightDTO);
    List<LightDTO> mapListOfYouths(List<Youth> youths);
    LightDTO mapStreet(Street street, @MappingTarget LightDTO lightDTO);
    List<LightDTO> mapListOfStreets(List<Street> streets);
}
