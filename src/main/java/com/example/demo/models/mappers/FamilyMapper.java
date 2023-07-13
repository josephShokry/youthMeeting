package com.example.demo.models.mappers;

import com.example.demo.models.DTOs.FamilyDTO;
import com.example.demo.models.Family;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FamilyMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Family familyDtoToFamily(FamilyDTO familyDTO, @MappingTarget Family family);
    FamilyDTO familyToFamilyDto(Family family, @MappingTarget FamilyDTO familyDTO);
}
