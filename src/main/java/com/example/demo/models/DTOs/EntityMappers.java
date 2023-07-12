package com.example.demo.models.DTOs;

import com.example.demo.models.Family;
import com.example.demo.models.Youth;
import com.example.demo.services.FamilyServices;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EntityMappers {
    // methods for youth
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
//    @Mapping(source = "familyId", target = "family")
    Youth getYouthFromDto(PersonDTO personDTO, @MappingTarget Youth youth);
    @Mapping(source = "family", target = "family")
    PersonDTO getPersonDTOFromYouth(Youth youth, @MappingTarget PersonDTO personDTO);
////////////////////////////////////////////
    // lightDTO for youth
    @Mapping(expression = "java(youth.getFirstName() + ' ' + youth.getLastName())", target = "name")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    YouthLightDTO getLightDTOFromYouth(Youth youth, @MappingTarget YouthLightDTO youthLightDTO);

/////////////////////////////////////
    // methods for family
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Family getFamilyFromDto(FamilyDTO familyDTO, @MappingTarget Family family);
    FamilyDTO getFamilyDTOFromFamily(Family family, @MappingTarget FamilyDTO familyDTO);

}
