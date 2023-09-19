package com.example.demo.models.mappers;

import com.example.demo.models.DTOs.FamilyDTO;
import com.example.demo.models.DTOs.LightDTO;
import com.example.demo.models.entities.Family;
import org.mapstruct.*;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FamilyMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Family familyDtoToFamily(FamilyDTO familyDTO, @MappingTarget Family family);
    FamilyDTO familyToFamilyDto(Family family, @MappingTarget FamilyDTO familyDTO);
    @Mapping(source = "familyName", target = "name")
    LightDTO familyToLightDto(Family family, @MappingTarget LightDTO lightDTO);

    default Iterable<LightDTO> familiesToLightDtos(Iterable<Family> families){
        return StreamSupport.stream(families.spliterator(), false)
                .map(entity -> familyToLightDto(entity,new LightDTO()))
                .collect(Collectors.toList());
    }
}
