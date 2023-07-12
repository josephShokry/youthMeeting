package com.example.demo.models.DTOs;

import com.example.demo.models.Family;
import com.example.demo.models.Youth;
import org.mapstruct.*;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EntityMappers {
    // methods for youth
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
//    @Mapping(source = "familyId", target = "family")
    Youth youthDtoToYouth(PersonDTO personDTO, @MappingTarget Youth youth);
    @Mapping(source = "family", target = "family")
    PersonDTO youthsToYouthLightDto(Youth youth, @MappingTarget PersonDTO personDTO);
////////////////////////////////////////////
    // lightDTO for youth
    @Mapping(expression = "java(youth.getFirstName() + ' ' + youth.getLastName())", target = "name")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    YouthLightDTO youthToYouthLightDto(Youth youth, @MappingTarget YouthLightDTO youthLightDTO);
//    @Mapping(expression = "java(youth.getFirstName() + ' ' + youth.getLastName())", target = "name")
    default Iterable<YouthLightDTO> youthsToYouthLightDto(Iterable<Youth> youths){
        return StreamSupport.stream(youths.spliterator(), false)
                .map(entity -> youthToYouthLightDto(entity,new YouthLightDTO()))
                .collect(Collectors.toList());
    }
/////////////////////////////////////
    // methods for family
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Family familyDtoToFamily(FamilyDTO familyDTO, @MappingTarget Family family);
    FamilyDTO familyToFamilyDto(Family family, @MappingTarget FamilyDTO familyDTO);
////////////////////////////////////////////
    //test
//    @Mapping(source = "family", target = "family")

}
