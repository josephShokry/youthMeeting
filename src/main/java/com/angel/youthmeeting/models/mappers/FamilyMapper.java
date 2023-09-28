package com.angel.youthmeeting.models.mappers;

import com.angel.youthmeeting.models.dtos.FamilyDTO;
import com.angel.youthmeeting.models.entities.Family;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FamilyMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Family mapFamilyDTO(FamilyDTO familyDTO, @MappingTarget Family family);
}
