package com.angel.youthmeeting.models.mappers;

import com.angel.youthmeeting.models.dtos.LightDTO;
import com.angel.youthmeeting.models.entities.Area;
import com.angel.youthmeeting.models.entities.Family;
import com.angel.youthmeeting.models.entities.Father;
import com.angel.youthmeeting.models.entities.Street;
import com.angel.youthmeeting.models.entities.Youth;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

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

    @Mapping(source = "firstName", target = "name")
    LightDTO fatherToLightDto(Father father, @MappingTarget LightDTO lightDTO);

    List<LightDTO> mapListOfFathers(List<Father> fathers);
}
