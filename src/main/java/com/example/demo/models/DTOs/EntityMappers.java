package com.example.demo.models.DTOs;

import com.example.demo.models.Family;
import com.example.demo.models.Person;
import com.example.demo.models.Youth;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EntityMappers {
//    EntityMappers INSTANCE = Mappers.getMapper(EntityMappers.class);
    // methods for youth
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Youth getYouthFromDto(PersonDTO personDTO, @MappingTarget Youth youth);
    PersonDTO getPersonDTOFromYouth(Youth youth, @MappingTarget PersonDTO personDTO);
////////////////////////////////////////////
    // lightDTO for youth
    @Mapping(expression = "java(youth.getFirstName() + ' ' + youth.getLastName())", target = "name")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    LightDTO getLightDTOFromYouth(Youth youth, @MappingTarget LightDTO lightDTO);

/////////////////////////////////////
    // methods for family
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Family getFamilyFromDto(FamilyDTO familyDTO, @MappingTarget Family family);
    FamilyDTO getFamilyDTOFromFamily(Family family, @MappingTarget FamilyDTO familyDTO);

}
