package com.angle.youthmeeting.models.mappers;

import com.angle.youthmeeting.models.entities.Family;
import com.angle.youthmeeting.models.dtos.FamilyDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FamilyMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Family mapFamilyDTO(FamilyDTO familyDTO, @MappingTarget Family family);
}
