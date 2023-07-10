package com.example.demo.models.DTOs;

import com.example.demo.models.Family;
import com.example.demo.models.Person;
import com.example.demo.models.Youth;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EntityMappers {
    // methods for youth
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Youth getYouthFromDto(PersonDTO personDTO, @MappingTarget Youth youth);
    PersonDTO getPersonDTOFromYouth(Youth youth, @MappingTarget PersonDTO personDTO);

    // methods for family
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Family getFamilyFromDto(FamilyDTO familyDTO, @MappingTarget Family family);
    FamilyDTO getFamilyDTOFromFamily(Family family, @MappingTarget FamilyDTO familyDTO);

}
